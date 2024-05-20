package com.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Main {
    private static final int MIN_OCTAVE = -3;
    private static final int MAX_OCTAVE = 5;
    private static final int NOTES_PER_OCTAVE = 12;

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java -jar task.jar <inputFile> <semitone> <outputFile>");
            System.exit(1);
        }

        String inputFile = args[0];
        int semitone = Integer.parseInt(args[1]);
        String outputFile = args[2];

        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(inputFile);
            Type listType = new TypeToken<List<int[]>>() {}.getType();
            List<int[]> notes = gson.fromJson(reader, listType);
            reader.close();

            for (int[] note : notes) {
                transpose(note, semitone);
                if (!isValid(note)) {
                    System.out.println("Error: Note out of range after transposition.");
                    System.exit(1);
                }
            }


            FileWriter writer = new FileWriter(outputFile);
            gson.toJson(notes, writer);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void transpose(int[] note, int semitone) {
        int totalNotes = note[0] * NOTES_PER_OCTAVE + note[1] + semitone;
        note[0] = totalNotes / NOTES_PER_OCTAVE;
        note[1] = totalNotes % NOTES_PER_OCTAVE;

        if (note[1] < 0) {
            note[1] += NOTES_PER_OCTAVE;
            note[0] -= 1;
        }
    }


    private static boolean isValid(int[] note) {
        return note[0] >= MIN_OCTAVE && note[0] <= MAX_OCTAVE && !(note[0] == MIN_OCTAVE && note[1] < 10) && !(note[0] == MAX_OCTAVE && note[1] > 1);
    }

}
