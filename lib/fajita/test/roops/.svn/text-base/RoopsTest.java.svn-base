package roops;

import java.io.File;

import ar.uba.dc.rfm.fajita.FajitaMain;

public abstract class RoopsTest {
	
	protected final void run_fajita(String case_study) {

		String curr_dir = System.getProperty("user.dir");

		String results_dir = curr_dir + File.separator + "results";
		String config_dir = curr_dir + File.separator + "config";
		String taco_properties_template = config_dir + File.separator + "taco.properties.template";
		String roops_dir = curr_dir + File.separator + "roops";
		String config_filename = config_dir + File.separator + getBenchmarkClass() + File.separator + case_study + ".fajita.config";

		String[] args = new String[] { "-rp", results_dir, "-tf", taco_properties_template, "-cp", roops_dir, "-cf", config_filename, "-r", "goal"/*, "-iu"*/ };

		//FajitaMain.main(args);

	}

	protected abstract String getBenchmarkClass();

}
