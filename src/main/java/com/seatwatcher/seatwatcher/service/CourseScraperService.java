package com.seatwatcher.seatwatcher.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class CourseScraperService {

    public int getAvailableSeats(
            String term,
            String courseCode,
            String section) {

        try {

            String department =
                    courseCode.substring(0, 4);

            String url =
                    "https://app.testudo.umd.edu/soc/"
                            + term
                            + "/"
                            + department
                            + "/"
                            + courseCode;

            Document document =
                    Jsoup.connect(url).get();

            String text = document.text();

            int sectionIndex =
                    text.indexOf(section);

            if (sectionIndex == -1) {
                return -1;
            }

            String afterSection =
                    text.substring(sectionIndex);

            int openIndex =
                    afterSection.indexOf("Open:");

            if (openIndex == -1) {
                return -1;
            }

            String afterOpen =
                    afterSection.substring(openIndex + 5)
                            .trim();

            String number =
                    afterOpen.split("[, )]")[0];

            return Integer.parseInt(number);

        } catch (Exception e) {

            System.out.println(
                    "Failed to retrieve course: "
                            + e.getMessage()
            );

            return -1;
        }
    }

    public Map<String, String> getAvailableTerms() {

        Map<String, String> terms = new LinkedHashMap<>();

        try {
            Document document =
                    Jsoup.connect("https://app.testudo.umd.edu/soc/").get();

            Elements options =
                    document.select("select option");

            for (Element option : options) {

                String value = option.attr("value");
                String text = option.text().trim();

                if (!value.isBlank()
                        && !text.isBlank()
                        && value.matches("\\d{6}")) {

                    terms.put(value, text);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed to retrieve terms: "
                            + e.getMessage()
            );
        }

        return terms;
    }
}
