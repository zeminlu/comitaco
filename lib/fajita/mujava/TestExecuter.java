////////////////////////////////////////////////////////////////////////////
// Module : TestExecuter.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava;

import java.io.*;
import java.lang.reflect.*;
import java.util.Vector;
import mujava.test.*;
import mujava.util.*;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 * @author Yu-Seung Ma
 * @version 1.0
  */

public class TestExecuter {
  Object lockObject = new Object();

  int TIMEOUT = 3000;
  final int MAX_TRY = 100;

  Class original_executer;
  Object original_obj;        // instancitation of the test set class
  volatile Object mutant_result;


  Class mutant_executer;      // test set class for a mutant
  volatile Object mutant_obj;          // test set object for a mutant

  Method[] testCases;
  volatile Method testcase;

  String whole_class_name;
  String testSet;
  boolean mutantRunning = true;

  public TestExecuter(String targetClassName) {

    int index = targetClassName.lastIndexOf(".");
    if(index<0){
      MutationSystem.CLASS_NAME = targetClassName;
    }else{
      MutationSystem.CLASS_NAME = targetClassName.substring(index+1,targetClassName.length());
    }

    MutationSystem.DIR_NAME = targetClassName;
    MutationSystem.CLASS_MUTANT_PATH = MutationSystem.MUTANT_HOME+"/"+targetClassName
                                      +"/"+MutationSystem.CM_DIR_NAME;
    MutationSystem.TRADITIONAL_MUTANT_PATH = MutationSystem.MUTANT_HOME+"/"+targetClassName
                                      +"/"+MutationSystem.TM_DIR_NAME;
    MutationSystem.EXCEPTION_MUTANT_PATH = MutationSystem.MUTANT_HOME+"/"+targetClassName
                                      +"/"+MutationSystem.EM_DIR_NAME;

    whole_class_name = targetClassName;

  }

	public void setTimeOut(int msecs){
		TIMEOUT = msecs;
	}

  public boolean readTestSet(String testSetName){
    try{
        testSet = testSetName;
        // Class loader for the original class
        OriginalLoader myLoader = new OriginalLoader();
        original_executer = myLoader.loadTestClass(testSet);
        original_obj = original_executer.newInstance();       // instancitation of the test set class
        if(original_obj==null){
          System.out.println(" Can't instantiace original object");
          return false;
        }

        // read testcases from the test set class
        testCases = original_executer.getDeclaredMethods();
        if(testCases==null){
          System.out.println(" No test case exist ");
          return false;
        }
    }catch(Exception e){
      System.err.println(e);
      return false;
    }
    return true;

  }

  boolean sameResult(Object result1,Object result2){
    if( !(result1.toString().equals(result2.toString())) ) return false;
    return true;
  }

  public TestResult runClassMutants()  throws NoMutantException,NoMutantDirException{
    MutationSystem.MUTANT_PATH = MutationSystem.CLASS_MUTANT_PATH;
    TestResult test_result = new TestResult();
    runMutants(test_result);
    return test_result;
  }

  public TestResult runExceptionMutants()  throws NoMutantException,NoMutantDirException{
    MutationSystem.MUTANT_PATH = MutationSystem.EXCEPTION_MUTANT_PATH;
    TestResult test_result = new TestResult();
    runMutants(test_result);
    return test_result;
  }

  public TestResult runTraditionalMutants(String methodSignature)  throws NoMutantException,NoMutantDirException{

    MutationSystem.MUTANT_PATH = MutationSystem.TRADITIONAL_MUTANT_PATH;
    String original_mutant_path = MutationSystem.MUTANT_PATH;

    TestResult test_result = new TestResult();

    if(methodSignature.equals("All method")){
        try{
          //setMutantPath();
          File f = new File(MutationSystem.TRADITIONAL_MUTANT_PATH,"method_list");
          FileReader r = new FileReader(f);
          BufferedReader reader = new BufferedReader(r);
          String readSignature = reader.readLine();
          while(readSignature!=null){
            MutationSystem.MUTANT_PATH = original_mutant_path+"/"+readSignature;
            try{
              runMutants(test_result);
            }catch(NoMutantException e){
            }
            readSignature = reader.readLine();
          }
          reader.close();
        }catch(Exception e){
          System.err.println("Error in update() in TraditioanlMutantsViewerPanel.java");
        }
    }else{
      MutationSystem.MUTANT_PATH = original_mutant_path+"/"+methodSignature;
      runMutants(test_result);
    }
    return test_result;
  }


  void runMutants(Object mutant,Method testcase) throws InterruptedException{
    mutantRunning = true;
    try{
      // testcase execution
      mutant_result = testcase.invoke(mutant_obj,null);
    }catch(Exception e){
      // execption occurred -> abnormal execution
      mutant_result = e.getCause().getClass().getName()+" : "  +e.getCause().getMessage();
    }
    mutantRunning = false;
    synchronized(lockObject){
      lockObject.notify();
    }
    //throw new InterruptedException();
  }

  synchronized void waitUntilAtLeast(long timeOut) throws InterruptedException{
    wait(timeOut);
  }


   private TestResult runMutants(TestResult tr) throws NoMutantException,NoMutantDirException{
    // The variable which has the information about the killed and live mutants
    try{
      // Read mutants
      File f = new File(MutationSystem.MUTANT_PATH);
      if(!f.exists()){
        System.err.println(" There is no directory for the mutants of " + MutationSystem.CLASS_NAME);
        System.err.println(" Please generate mutants for " + MutationSystem.CLASS_NAME);
        throw new NoMutantDirException();
      }

      // mutantDirectories match the names of mutants
      String[] mutantDirectories = f.list(new MutantDirFilter());
      if(mutantDirectories==null || mutantDirectories.length==0){
        System.err.println(" No mutant is exist for " + MutationSystem.CLASS_NAME);
       // System.err.println(" Please check if zero mutant is correct.");
        throw new NoMutantException();
      }

      int mutant_num = mutantDirectories.length;
      for(int i=0;i<mutant_num;i++){
          // set live mutnats
          tr.live_mutants.add(mutantDirectories[i]);
      }

      // result againg original class for each test case
      Object[] original_results = new Object[testCases.length];
      // list of the names of killed mutants with each test case
      String[] killed_mutants = new String[testCases.length];

      Debug.println("\n\n==== Generating original results ========================================");

      for(int k=0;k<testCases.length;k++){
        killed_mutants[k]="";   // At first, no mutant is killed by k the test case

        // test로 시작되는 메소드만 수행
        String testName = testCases[k].getName();
        if( !(testName.startsWith("test")) ) continue;
        testcase = original_executer.getMethod(testName,null);
        try{
          // testcase execution
          original_results[k] = testcase.invoke(original_obj,null);
          Debug.println("Result for " + testName + "  :  " +original_results[k] );
        }catch(Exception e){
          // exceptino occurred -> abnormal execution
          original_results[k] = e.getCause().getClass().getName()+" : "  +e.getCause().getMessage();
          Debug.println("Result for " + testName + "  :  " +original_results[k] );
          Debug.println(" [warining] " + testName + " generate exception as a result " );
           // ----------------------------------
        }finally{
            //originalResultFileRead();
        }
      }


      Debug.println("\n\n*Executing Mutants");
      for(int i=0; i<tr.live_mutants.size(); i++){
        // read the information for the "i"th live mutant
        String mutant_name = tr.live_mutants.get(i).toString();
        JMutationLoader mutantLoader = new JMutationLoader(mutant_name);
        //mutantLoader.loadMutant();
        mutant_executer = mutantLoader.loadTestClass(testSet);
        mutant_obj = mutant_executer.newInstance();
        Debug.print("  " +mutant_name);

        for(int k=0;k<testCases.length;k++){
          String testName = testCases[k].getName();
            try{
              if( !(testName.startsWith("test")) ) continue;
              testcase = mutant_executer.getMethod(testName,null);

              // Mutants are runned using Thread to detect infinite loop caused by mutation
              Runnable r= new Runnable(){
                public void run(){
                  try{
                    runMutants(mutant_obj,testcase);
                  }catch(Exception e){
                    e.printStackTrace();
                  }
                }
              };

              Thread t = new Thread(r);
              t.start();

              synchronized(lockObject){
                lockObject.wait(TIMEOUT); // Chek out if a mutant is in infinite loop
              }
              if(mutantRunning){
                t.interrupt();
                mutant_result = "time_out: more than " + TIMEOUT + " seconds";
              }
            }catch(Exception e){
              mutant_result = e.getCause().getClass().getName()+" : "  +e.getCause().getMessage();
            }

            // #######################################################################
            // ##  (2) If you don't want to see the result,
            // ##      add comment symbol "//" to the below statement
            // #######################################################################
            if(mutant_result==null){
              mutant_result = "null";
              Debug.println2(" - " +testName + " = null     ");
                tr.killed_mutants.add(mutant_name);
                killed_mutants[k] = killed_mutants[k] + mutant_name +" " ;
                break;
            }else{
              Debug.println2(" - " +testName + " = " + mutant_result.toString()+ "     ");

              // Compare thest result
              if(sameResult(original_results[k],mutant_result)){ // live mutant

              }else{ // killed mutant
                  tr.killed_mutants.add(mutant_name);
                  killed_mutants[k] = killed_mutants[k] + mutant_name +" " ;
                  break;
              }
            }
        }
        mutantLoader = null;
        mutant_executer = null;
        System.gc();
      }

      for(int i=0;i<tr.killed_mutants.size();i++){
        tr.live_mutants.remove(tr.killed_mutants.get(i));


      }

      System.out.println(" Analysis of testcases ");
      for(int i=0;i<killed_mutants.length;i++){
        System.out.println("  test " + (i+1) + "  kill  ==> " + killed_mutants[i]);
      }

    }catch(NoMutantException e1){
      throw e1;
    }catch(NoMutantDirException e2){
      throw e2;
    }catch(ClassNotFoundException e3){
      System.err.println("[Execution 1] " + e3);
      return null;
    }catch(Exception e){
      System.err.println("[Exception 2]" + e);
      return null;
    }
    return tr;
  }

  void erase_killed_mutants(Vector v){
    System.out.println("Deleting directories of killed mutants");
    for(int i=0;i<v.size();i++){
      System.out.print(v.get(i).toString()+" ");
      erase_directory(v.get(i).toString());
    }
  }

  void erase_directory(String mutant_name){
    File mutant_dir = new File(MutationSystem.MUTANT_PATH+"/"+mutant_name);
    File[] f = mutant_dir.listFiles();
    boolean flag = false;
    for(int i=0;i<f.length;i++){
      while(!flag){
        flag = f[i].delete();
      }
      flag = false;
    }

    while(!flag){
      flag = mutant_dir.delete();
    }
  }
}
