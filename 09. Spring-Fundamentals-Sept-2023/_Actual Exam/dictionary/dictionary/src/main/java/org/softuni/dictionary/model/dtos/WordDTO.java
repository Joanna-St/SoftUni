package org.softuni.dictionary.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.softuni.dictionary.util.Messages;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record WordDTO(@Size(min = 2, max = 50, message = Messages.LENGTH_TERM)
                      String term,
                      @Size(min = 2, max = 80, message = Messages.LENGTH_TRANSLATION)
                      String translation,
                      @Size(min = 2, max = 200, message = Messages.LENGTH_EXAMPLE)
                      String example,
                      @NotNull(message = Messages.BLANK_DATE)
                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                      @PastOrPresent(message = Messages.PAST_OR_PRESENT)
                      LocalDate inputDate,
                      @NotBlank(message = Messages.BLANK_LANGUAGE)
                      String language) {
}
