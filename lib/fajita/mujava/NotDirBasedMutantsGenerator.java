package mujava;

import java.io.File;
import java.util.Set;

import mujava.api.Mutant;
import mujava.api.MutantsInformationHolder;
import mujava.op.PRV;
import mujava.op.PRVO;
import mujava.op.PRVV;
import mujava.op.basic.AODS;
import mujava.op.basic.AODU;
import mujava.op.basic.AOIS;
import mujava.op.basic.AOIU;
import mujava.op.basic.AORB;
import mujava.op.basic.AORS;
import mujava.op.basic.AORU;
import mujava.op.basic.ASRS;
import mujava.op.basic.COD;
import mujava.op.basic.COI;
import mujava.op.basic.COR;
import mujava.op.basic.LOD;
import mujava.op.basic.LOI;
import mujava.op.basic.LOR;
import mujava.op.basic.ROR;
import mujava.op.basic.SOR;
import mujava.op.util.CodeChangeLog;
import mujava.util.Debug;
import openjava.ptree.ClassDeclaration;
import openjava.ptree.ClassDeclarationList;
import openjava.ptree.ParseTreeException;

public class NotDirBasedMutantsGenerator extends MutantsGenerator {

	private Set<Mutant> mutOps;
		
	public NotDirBasedMutantsGenerator(File f, Set<Mutant> mutOps) {
		super(f);
		this.mutOps = mutOps;
	}
	
	@Override
	void arrangeOriginal() {
		return;
	}

	@Override
	void compileOriginal() {
		return;
	}

	@Override
	void genMutants() {
		// Code taken from genTraditionalMutants.
		if (comp_unit == null) {
			System.err.println (original_file + " is skipped.");
		}
		MutantsInformationHolder.mainHolder().setCompilationUnit(comp_unit);
		ClassDeclarationList cdecls = comp_unit.getClassDeclarations();
		if (cdecls == null || cdecls.size() == 0) {
			return;
		}
		if (mutOps != null && mutOps.size() > 0) {
			CodeChangeLog.openLogFile();
			generateMutants(cdecls);
			CodeChangeLog.closeLogFile();
		}

	}

	private void generateMutants(ClassDeclarationList cdecls) {
		// Code taken from genTraditionalMutans
		
		for (int j = 0 ; j < cdecls.size() ; ++j ) {
			ClassDeclaration cdecl = cdecls.get(j);
			if (cdecl.getName().equals(MutationSystem.CLASS_NAME)) {
				try {
					mujava.op.util.Mutator mutant_op;
					boolean AOR_FLAG = true;
					if (mutOps.contains(Mutant.AORB)) {
						Debug.println("  Applying AOR-Binary ... ... ");
//						AOR_FLAG = true;
						mutant_op = new AORB(file_env, cdecl, comp_unit);
						comp_unit.accept(mutant_op);
					}
					if (mutOps.contains(Mutant.AORS)) {
						Debug.println("  Applying AOR-Short-Cut ... ... ");
//						AOR_FLAG = true;
						mutant_op = new AORS(file_env, cdecl, comp_unit);
						comp_unit.accept(mutant_op);
					}
					if (mutOps.contains(Mutant.AORU)) {
						Debug.println("  Applying AOR-Normal-Unary ... ... ");
//						AOR_FLAG = true;
						mutant_op = new AORU(file_env,cdecl,comp_unit);
						comp_unit.accept(mutant_op);
					}
					if (mutOps.contains(Mutant.AODU)) {
						Debug.println("  Applying AOD-Normal-Unary ... ... ");
						mutant_op = new AODU(file_env, cdecl, comp_unit);
						((AODU)mutant_op).setAORflag(AOR_FLAG);
						comp_unit.accept(mutant_op);
					}
					if (mutOps.contains(Mutant.AODS)) {
						Debug.println("  Applying AOD-Short-Cut ... ... ");
						mutant_op = new AODS(file_env, cdecl, comp_unit);
						comp_unit.accept(mutant_op);
					}
					if (mutOps.contains(Mutant.AOIU)) {
						Debug.println("  Applying AOI-Normal-Unary ... ... ");
						mutant_op = new AOIU(file_env,cdecl,comp_unit);
						((AOIU)mutant_op).setAORflag(AOR_FLAG);
						comp_unit.accept(mutant_op);
					}
					if (mutOps.contains(Mutant.AOIS)) {
						Debug.println("  Applying AOI-Short-Cut ... ... ");
						mutant_op = new AOIS(file_env, cdecl, comp_unit);
						comp_unit.accept(mutant_op);
					}
					if (mutOps.contains(Mutant.ROR)) {
						Debug.println("  Applying ROR ... ... ");
						mutant_op = new ROR(file_env, cdecl, comp_unit);
						comp_unit.accept(mutant_op);
					}
					if (mutOps.contains(Mutant.COR)) {
						Debug.println("  Applying COR ... ... ");
						mutant_op = new COR(file_env, cdecl, comp_unit);
						comp_unit.accept(mutant_op);
					}
					if (mutOps.contains(Mutant.COD)) {
						Debug.println("  Applying COD ... ... ");
						mutant_op = new COD(file_env, cdecl, comp_unit);
						comp_unit.accept(mutant_op);
					}
					if (mutOps.contains(Mutant.COI)) {
						Debug.println("  Applying COI ... ... ");
						mutant_op = new COI(file_env, cdecl, comp_unit);
						comp_unit.accept(mutant_op);
					}
					if (mutOps.contains(Mutant.SOR)) {
						Debug.println("  Applying SOR ... ... ");
						mutant_op = new SOR(file_env, cdecl, comp_unit);
						comp_unit.accept(mutant_op);
					}
					if (mutOps.contains(Mutant.LOR)) {
						Debug.println("  Applying LOR ... ... ");
						mutant_op = new LOR(file_env, cdecl, comp_unit);
						comp_unit.accept(mutant_op);
					}
					if (mutOps.contains(Mutant.LOI)) {
						Debug.println("  Applying LOI ... ... ");
						mutant_op = new LOI(file_env, cdecl, comp_unit);
						comp_unit.accept(mutant_op);
					}
					if (mutOps.contains(Mutant.LOD)) { 
						Debug.println("  Applying LOD ... ... ");
						mutant_op = new LOD(file_env, cdecl, comp_unit);
						comp_unit.accept(mutant_op);
					}
					if (mutOps.contains(Mutant.ASRS)) {
						Debug.println("  Applying ASR-Short-Cut ... ... ");
						mutant_op = new ASRS(file_env, cdecl, comp_unit);
						comp_unit.accept(mutant_op);
					}
					if (mutOps.contains(Mutant.PRV)) {
						Debug.println("  Applying PRV ... ... ");
						mutant_op = new PRV(file_env, cdecl, comp_unit);
						comp_unit.accept(mutant_op);
					}
					
					if (mutOps.contains(Mutant.PRVV)) {
						Debug.println("  Applying PRVV ... ... ");
						mutant_op = new PRVV(file_env, cdecl, comp_unit);
						comp_unit.accept(mutant_op);
					}
					
					if (mutOps.contains(Mutant.PRVO)) {
						Debug.println("  Applying PRVO ... ... ");
						mutant_op = new PRVO(file_env, cdecl, comp_unit);
						comp_unit.accept(mutant_op);
					}
					
				} catch (ParseTreeException e) {
					System.err.println("Exception during generating  mutants for the class.");
					e.printStackTrace();
				}
			}
		}
	}
}