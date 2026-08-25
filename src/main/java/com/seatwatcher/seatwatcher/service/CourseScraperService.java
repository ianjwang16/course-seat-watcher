package com.seatwatcher.seatwatcher.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class CourseScraperService {

    public int getAvailableSeats(
            String courseCode,
            String section) {

        try {

            String department =
                    courseCode.substring(0, 4);

            String url =
                    "https://app.testudo.umd.edu/soc/202608/"
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
}
