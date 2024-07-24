package com.perfect.public_data.domain.general.restaurant.partitioner;

import com.perfect.public_data.global.util.CsvUtil;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class GeneralRestaurantPartitioner implements Partitioner {

    private final Resource resource;
    private final int partitionSize;

    public GeneralRestaurantPartitioner(int partitionSize) {
        this.resource = CsvUtil.getGeneralRestaurantCsv();
        this.partitionSize = partitionSize;
    }

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> partitions = new HashMap<>();
        int totalLines = countLinesInFile();

        int partitionNumber = 0;
        try {
            for (int i = 1; i < totalLines; i += partitionSize) {
                ExecutionContext context = new ExecutionContext();
                context = checkStartLine(context, i);
                context.putInt("endLine", Math.min((i + partitionSize) - 1, totalLines));
                context.putString("filePath", resource.getURI().getPath());

                partitions.put("partition" + partitionNumber, context);
                partitionNumber++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return partitions;
    }

    public ExecutionContext checkStartLine(ExecutionContext context, int startLine) {
        if (startLine == 1) {
            startLine = 2;
        }
        context.putInt("startLine", startLine);
        return context;
    }

    public int countLinesInFile() {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            while (reader.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}