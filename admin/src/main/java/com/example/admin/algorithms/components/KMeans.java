package com.example.admin.algorithms.components;

import java.util.ArrayList;
import java.util.List;

import com.example.admin.algorithms.AlComponent;
import com.example.admin.algorithms.ComponentType;
import com.example.admin.algorithms.Params;
import com.example.admin.algorithms.RunResult;
import com.example.admin.service.RunUtil;



import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KMeans extends AlComponent {

    private static String pyFile = "kMeans.py";

    private RunUtil runUtil = new RunUtil();

    public KMeans() {
        params = new FileParams();
        type = ComponentType.LOCAL_PYTHON;
        name = "KMeans";
    }

    @Override
    public RunResult run(String root, String inFile, String outFile) {
        List<String> sParams = new ArrayList<String>();
        for (String key : params.getParams().keySet()) {
            String value = params.getParam(key);
            if(value.length() == 0){
                continue;
            }
            log.info(key);
            log.info(value);
            sParams.add("--" + key);
            sParams.add(value);
        }
        sParams.add("--inFile");
        sParams.add(inFile);
        sParams.add("--outFileName");
        sParams.add(outFile);
        sParams.add("--root");
        sParams.add(root);
        RunResult result = runUtil.runPython(pyFile, sParams);
        return result;
    }

    private class FileParams extends Params {
        FileParams() {
            setParam("train", "True");
            setParam("model_name", "kmeans_test");
            setParam("model","");
            setParam("n_clusters", "0");
<<<<<<< HEAD
            setParam("n_int", "10");
            setParam("max_iter", "300");
=======
>>>>>>> 26834db2e373429b3393ac8503d74372ba3ef35f
        }
    }

}