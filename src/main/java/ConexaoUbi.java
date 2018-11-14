import com.ubidots.*;

public class ConexaoUbi {



	
	public double getPotencia() {

		ApiClient api = new ApiClient("A1E-66dfd88e931cd31120f1777cfe47ae3260d8");

		DataSource[] dataSources = api.getDataSources();

		for (DataSource ds : dataSources) {

			Variable[] potencia = ds.getVariables();

			for (Variable variable : potencia) {
				Value[] values = variable.getValues();
				return values[0].getValue();
				

			}
		}
		return 0.0;

	}
}
