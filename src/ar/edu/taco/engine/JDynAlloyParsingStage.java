/*
 * TACO: Translation of Annotated COde
 * Copyright (c) 2010 Universidad de Buenos Aires
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA,
 * 02110-1301, USA
 */

package ar.edu.taco.engine;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ar.edu.taco.TacoConfigurator;
import ar.edu.taco.TacoException;
import ar.edu.jdynalloy.ast.JDynAlloyModule;
import ar.edu.jdynalloy.binding.fieldcollector.FieldCollectorVisitor;
import ar.edu.jdynalloy.binding.symboltable.FieldDescriptor;
import ar.edu.jdynalloy.binding.symboltable.SymbolTable;
import ar.edu.jdynalloy.parser.JDynAlloyParserManager;
import ar.edu.jdynalloy.parser.JDynAlloyParsingException;
import ar.edu.jdynalloy.parser.JDynAlloyProgramParseContext;
import ar.uba.dc.rfm.alloy.AlloyTyping;
import ar.uba.dc.rfm.alloy.AlloyVariable;
import ar.uba.dc.rfm.alloy.ast.formulas.AlloyFormula;

public class JDynAlloyParsingStage implements ITacoStage {
	private List<JDynAlloyModule> parsedModules;
	private List<JDynAlloyModule> givenModules;

	public List<JDynAlloyModule> getParsedModules() {
		return parsedModules;
	}

	public JDynAlloyParsingStage(List<JDynAlloyModule> givenModules) {
		this.givenModules = givenModules;
		this.parsedModules = new ArrayList<JDynAlloyModule>();
	}

	@Override
	public void execute() {

		try {
			// first pass
			List<JDynAlloyModule> firstPassModules = new ArrayList<JDynAlloyModule>();
			makeParserPass(firstPassModules, null);
			firstPassModules.addAll(this.givenModules);

			// retrieve all fields
			SymbolTable symbolTable = new SymbolTable();
			FieldCollectorVisitor fieldCollectorVisitor = new FieldCollectorVisitor(symbolTable, TacoConfigurator.getInstance().getUseJavaArithmetic());
			for (JDynAlloyModule aModule : firstPassModules) {
				aModule.accept(fieldCollectorVisitor);
				
			}

			Set<AlloyVariable> fields = new HashSet<AlloyVariable>();
			for (FieldDescriptor fieldDescriptor : symbolTable.getFieldSet()) {
				String aFieldName = fieldDescriptor.getFieldName();
				AlloyVariable variable = AlloyVariable.buildAlloyVariable(aFieldName);
				fields.add(variable);
			}

			// create new context
			JDynAlloyProgramParseContext ctx = new JDynAlloyProgramParseContext(new HashSet<AlloyVariable>(), fields, true);

			// second pass modules
			makeParserPass(this.parsedModules, ctx);

		} catch (FileNotFoundException e) {
			throw new TacoException(e);
		}

	}

	@SuppressWarnings("unchecked")
	private void makeParserPass(List<JDynAlloyModule> resultModules, JDynAlloyProgramParseContext ctx) throws JDynAlloyParsingException, FileNotFoundException {
		List<String> sourceFiles = (List<String>)TacoConfigurator.getInstance().getList(TacoConfigurator.JDYNALLOY_PARSER_INPUT_FILES, new ArrayList<String>());
		for (String jDynalloySourceFile : sourceFiles) {
			List<JDynAlloyModule> parsedModules = JDynAlloyParserManager.parseModulesFile(jDynalloySourceFile, ctx);
			resultModules.addAll(parsedModules);
		}

		Set<String> resourceFiles = TacoConfigurator.getInstance().getJDynAlloyParserInputResources();
		for (String jDynalloySourceFile : resourceFiles) {

			List<JDynAlloyModule> parsedModules = JDynAlloyParserManager.parseModulesResource(jDynalloySourceFile, ctx);
			parsedModules.get(0).setPredsEncodingValueOfArithmeticOperationsInObjectInvariants(new LinkedList<AlloyFormula>());
			parsedModules.get(0).setVarsEncodingValueOfArithmeticOperationsInObjectInvariants(new AlloyTyping());
			resultModules.addAll(parsedModules);

		}
	}
}
