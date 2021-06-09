package application;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kmeans {
	public static Map<double[], Integer> kmeans(List<double[]> features,int distance, Map<Integer, double[]> centroids, int k) {
		Map<double[], Integer> clusters = new HashMap<>();
		int k1 = 0;
		double dist=0.0;
		for(double[] x:features) {
			double minimum = 999999.0;
			for (int j = 0; j < k; j++) {
				if(distance==1){
				 dist = Distance.eucledianDistance(centroids.get(j), x);
				// System.out.println(x[0]+" , "+ x[1]+" , "+ dist);
				}
				else if(distance==2){
					dist = Distance.manhattanDistance(centroids.get(j), x);
				}
				if (dist < minimum) {
					minimum = dist;
					k1 = j;
				}
			
			}
			clusters.put(x, k1);
		}
		
		return clusters;

	}
	public static Map<Integer, double[]> initialCentroid(List<double[]> features,Map<Integer, double[]> centroids,int k){
		double[] x1 = new double[2];

        for (int i = 0; i < k; i++) {
			x1=features.get(i);
			centroids.put(i, x1);
		}
        return centroids;
	}
    public static double[] centroidCalculator(List<double[]> a) {
		int count = 0;
		double sum=0.0;
		double[] centroids = new double[Data.nbFeatures];
		for (int i = 0; i < Data.nbFeatures; i++) {
			sum=0.0;
			count = 0;
			for(double[] x:a){
				count++;
				sum = sum + x[i];
			}
			centroids[i] = sum / count;
		}
		return centroids;

	}
    public static Map<double[], Integer> Clustering(List<double[]> features, int distance , Map<Integer, double[]> centroids , int k,int max_itr ){
    	Map<double[], Integer> clusters = new HashMap<>();
        clusters = kmeans(features, distance, centroids, k);
        double db[] = new double[2];
        for (int i = 0; i < max_itr; i++) {
			for (int j = 0; j < k; j++) {
				List<double[]> list = new ArrayList<>();
				for (double[] key : clusters.keySet()) {
					if (clusters.get(key)==j) {
						list.add(key);
					}
			}
				db = Kmeans.centroidCalculator(list);
				centroids.put(j, db);
			}
		clusters.clear();
		clusters = Kmeans.kmeans(features,distance, centroids, k);
		}
    	return clusters;
    }
	
}
