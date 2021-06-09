package application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Data {

	static int nbFeatures ;
	protected List<double[]> features=new ArrayList<>();
	protected List<String> label=new ArrayList<>();
	
	 void Read(String str) {
		File file = new File(str);
		try(BufferedReader readFile=new BufferedReader(new FileReader(file))){
			String line;
			while((line=readFile.readLine()) != null)
			{
				 String[] data = line.split(",");
				 double[] feature = new double[2];
          		 nbFeatures = 2;
          		for (int i = 0; i < nbFeatures; i++)
          			 feature[i] = Double.parseDouble(data[i]);
          		features.add(feature);
          		label.add(data[2]);
          		//System.out.println(data[2]);
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	
	
	void display()
	{
		Iterator<double[]> itr=features.iterator();
		Iterator<String> sitr=label.iterator();
		while(itr.hasNext())
		{ 
			double db[]=itr.next();
			for(int i=0; i<2;i++)
		{
			System.out.print(db[i]+" ");
		}	
			String s=sitr.next() ;
			System.out.println(s);
			//System.out.println();
		}
		
	}
}
