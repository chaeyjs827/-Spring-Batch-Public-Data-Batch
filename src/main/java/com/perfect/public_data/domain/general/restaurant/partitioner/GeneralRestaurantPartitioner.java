package com.perfect.public_data.domain.general.restaurant.partitioner;

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

    public GeneralRestaurantPartitioner(Resource resource, int partitionSize) {
        this.resource = resource;
        this.partitionSize = partitionSize;
    }

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> partitions = new HashMap<>();
        int totalLines = countLinesInFile();

        int partitionNumber = 0;
        for (int i = 0; i < totalLines; i += partitionSize) {
            ExecutionContext context = new ExecutionContext();
            context.putInt("startLine", i);
            context.putInt("endLine", Math.min(i + partitionSize, totalLines));
            context.putString("filePath", resource.getFilename());
            partitions.put("partition" + partitionNumber, context);
            partitionNumber++;
        }

        return partitions;
    }

    private int countLinesInFile() {
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