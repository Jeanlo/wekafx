package edu.pucmm.mineriadedatos2017.algoritmos;

import org.controlsfx.control.textfield.CustomTextField;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.File;

public class AlgoritmoJ48 {
    private File file;
    private CustomTextField customTextField;

    public AlgoritmoJ48(File file, CustomTextField customTextField) {
        this.file = file;
        this.customTextField = customTextField;
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

            Instance inst = new DenseInstance(data.numAttributes());
            inst.setDataset(data);

            String valoresSet = "sunny,cool,high,TRUE,no";
            String [] valores = valoresSet.split(",");
            for(int i = 0; i < data.numAttributes(); i++){
                inst.setValue(i, valores[i]);
            }
            data.add(inst);

            fc.buildClassifier(data);

            for (int i = 0; i < data.numInstances(); i++) {
                double pred = fc.classifyInstance(data.instance(i));
                stringBuilder.append("No. " + (i+1) + "\n");
                stringBuilder.append(data.instance(i).toString() + "\n");
                stringBuilder.append("Valor predecido: " + data.classAttribute().value((int) pred) + "\n");
                stringBuilder.append("Valor real: " + data.classAttribute().value((int) data.instance(i).classValue()) + "\n\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringBuilder;
    }
}
