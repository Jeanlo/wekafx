package edu.pucmm.mineriadedatos2017.algoritmos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.File;
import java.io.IOException;

public class AlgoritmoNaiveBayes {
    private static final Logger logger = LogManager.getLogger();

    private File file;

    public AlgoritmoNaiveBayes(File file) {
        this.file = file;
    }

    public StringBuilder imprimir() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            ArffLoader arffLoader = new ArffLoader();
            arffLoader.setFile(file);

            Instances instances = arffLoader.getStructure();
            instances.setClassIndex(instances.numAttributes() - 1);

            NaiveBayesUpdateable naiveBayesUpdateable = new NaiveBayesUpdateable();
            naiveBayesUpdateable.buildClassifier(instances);
            Instance current;

            while ((current = arffLoader.getNextInstance(instances)) != null) {
                naiveBayesUpdateable.updateClassifier(current);
                stringBuilder.append(naiveBayesUpdateable);
            }

            return stringBuilder;
        } catch (IOException e) {
            logger.debug("Error al cargar el archivo arff.", e);
        } catch (Exception e) {
            logger.debug("Error al procesar el archivo arff.", e);
        }
        return null;
    }
}
