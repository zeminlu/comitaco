package ar.edu.taco.stryker.api.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.apache.log4j.Logger;

import ar.edu.taco.TacoAnalysisResult;
import ar.edu.taco.TacoMain;
import ar.edu.taco.stryker.api.impl.input.DarwinistInput;
import ar.uba.dc.rfm.dynalloy.analyzer.AlloyAnalysisResult;

import com.google.common.collect.Lists;
import com.google.common.io.Files;

public class DarwinistController extends AbstractBaseController<DarwinistInput> {

	private static Logger log = Logger.getLogger(DarwinistController.class);

	private static DarwinistController instance;
	
	private List<String> resolvedBugs = Lists.newArrayList();
	
	synchronized static DarwinistController getInstance() {
		if(instance == null) {
			instance = new DarwinistController();
		}
		return instance;
	}
	
	ExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	
	private DarwinistController() {
		
	}
	
	@Override
	protected Runnable getRunnable() {
		return new Runnable() {

			TacoMain tacoMain = new TacoMain();

			@Override
			public void run() {
				while (!willShutdown.get() || !queue.isEmpty()) {
					DarwinistInput input = null;
					try {
						input = queue.take();
						log.warn("Queue size: "+queue.size());
						if(input.getFilename() == null) {
							shutdown();
							break;
						}
						final String configurationFile = input.getConfigurationFile();
						final Properties props = new Properties(input.getOverridingProperties());
						Properties oldProps = input.getOverridingProperties();
						for(Entry<Object,Object> o : oldProps.entrySet()){
							props.put(o.getKey(), o.getValue());
						}
						String filename = input.getFilename();
						String originalFilename = input.getOriginalFilename();
						
						File originalFile = new File(originalFilename);
//						originalFile.delete();

						File newFile = new File(originalFilename+"_temp");
						newFile.createNewFile();
						
						Files.copy(originalFile, newFile);
						
						File newTestFile = new File(filename);
						newFile.createNewFile();
						
						Files.copy(newTestFile, originalFile);
						
						
//						Main compiler = new Main(new PrintWriter(System.out), new PrintWriter(System.err), 
//								false/*systemExit*/, null/*options*/, null/*progress*/);
						String currentClasspath = System.getProperty("java.class.path");
//						compiler.compile(new String[]{"-classpath", currentClasspath, originalFilename});
//						props.put("methodToCheck", input.getMethod()+"_0");
						JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
						int compilationResult =	compiler.run(null, null, null, new String[]{"-classpath", currentClasspath, originalFilename});
						if(compilationResult == 0){
							log.warn("Compilation is successful: "+filename);
							props.put("attemptToCorrectBug",false);
							props.put("generateUnitTestCase",false);
							TacoAnalysisResult analysis_result = tacoMain.run(configurationFile, props);
							AlloyAnalysisResult analysisResult = analysis_result.get_alloy_analysis_result();
							if(analysisResult == null || analysisResult.isSAT()) {
								log.warn(filename + " didnt solve the problem");
//								File f = new File(filename);
//								f.delete();
							} else {
								//solution found
								resolvedBugs.add(input.getFilename());
								log.error("Solution: "+input.getFilename());
								MuJavaController.getInstance().shutdownNow();
								OpenJMLController.getInstance().shutdownNow();
								shutdown();
								queue.clear();
							}
						}else{
							log.info("Compilation Failed");
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (Exception e) {
						log.warn("Exception e: "+e.getLocalizedMessage());
					} finally {
						log.debug("Entering finally");
						if(input != null && input.getOriginalFilename() != null) {
							log.debug("Inside the if of finally");
							String originalFilename = input.getOriginalFilename();
							File originalFile = new File(originalFilename);
	
							File newFile = new File(originalFilename+"_temp");
							
							originalFile.delete();
							
							try {
								log.debug("Restoring file: "+originalFile);
								Files.copy(newFile, originalFile);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		};
	}

	@Override
	protected int getQtyOfThreads() {
		return 1;
	}

	public List<String> getResolvedBugs() {
		return resolvedBugs;
	}

}
