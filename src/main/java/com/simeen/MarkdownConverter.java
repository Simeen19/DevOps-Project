package com.simeen;

import java.io.*;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;

public class MarkdownConverter {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: java MarkdownConverter <input.md> <output.html>");
            return;
        }

        String inputPath = args[0];
        String outputPath = args[1];

        String markdown = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(inputPath)));

        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        Node document = parser.parse(markdown);
        String html = renderer.render(document);

        try (FileWriter writer = new FileWriter(outputPath)) {
            writer.write(html);
            System.out.println("Converted successfully: " + outputPath);
        }
    }
}
