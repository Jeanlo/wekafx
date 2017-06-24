package edu.pucmm.mineriadedatos2017.algoritmos;

import java.io.File;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.trees.J48;

public class AlgoritmoJ48 {
    private File file;

    public AlgoritmoJ48(File file) {
        this.file = file;
    }

    public StringBuilder imprimir() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            DataSource source = new DataSource(this.file.getAbsolutePath());
            Instances data = source.getDataSet();

            if (data.classIndex() == -1)
                data.setClassIndex(data.numAttributes() - 1);

            J48 j48 = new J48();
            j48.setUnpruned(true);
            FilteredClassifier fc = new FilteredClassifier();
            fc.setClassifier(j48);
            fc.buildClassifier(data);

            for (int i = 0; i < data.numInstances(); i++) {
                double pred = fc.classifyInstance(data.instance(0));
                stringBuilder.append("No. " + data.instance(i).value(0) + "\n");
                stringBuilder.append(data.instance(i).toString() + "\n");
                stringBuilder.append("Valor predecido: " + data.classAttribute().value((int) pred) + "\n");
                stringBuilder.append("Valor real: " + data.classAttribute().value((int) data.instance(i).classValue()) + "\n\n");

                /*System.out.print("ID: " + data.instance(i).value(0));
                System.out.print(", actual: " + data.classAttribute().value((int) data.instance(i).classValue()));
                System.out.println(", predicted: " + data.classAttribute().value((int) pred));
                System.out.println(data.instance(2).toString());*/
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringBuilder;
    }
}
