import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.trees.J48;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ruta = scanner.next();

        try {
            DataSource source = new DataSource("C:\\Program Files\\Weka-3-8\\data\\" + ruta + ".arff");
            Instances data = source.getDataSet();

            if (data.classIndex() == -1)
                data.setClassIndex(data.numAttributes() - 1);

            J48 j48 = new J48();
            j48.setUnpruned(true);
            FilteredClassifier fc = new FilteredClassifier();
            fc.setClassifier(j48);
            fc.buildClassifier(data);
            for (int i = 0; i < data.numInstances(); i++) {
                double pred = fc.classifyInstance(data.instance(i));
                System.out.print("ID: " + data.instance(i).value(0));
                System.out.print(", actual: " + data.classAttribute().value((int) data.instance(i).classValue()));
                System.out.println(", predicted: " + data.classAttribute().value((int) pred));
                System.out.println(data.instance(2).toString());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
