package ru.webapp.util;

import ru.webapp.model.Section;
import ru.webapp.model.SectionType;
import ru.webapp.model.TextSection;
import ru.webapp.model.TextSectionWithTitle;

import java.util.Arrays;
import java.util.Collections;

import static ru.webapp.web.HtmlUtil.input;
import static ru.webapp.web.HtmlUtil.textArea;

public enum SectionHtmlType {
    TEXT {
        @Override
        public String toHtml(Section section, SectionType type) {
            return input(type.name(), section == null ? "" : ((TextSection) section).getValue());
        }

        @Override
        public TextSection createSection(String value) {
            return new TextSection(value);
        }
    },
    MULTI_TEXT {
        @Override
        public String toHtml(Section section, SectionType type) {
            return textArea(type.name(), section == null ? Collections.singletonList("") :((TextSectionWithTitle) section).getValues());
        }

        @Override
        public TextSectionWithTitle createSection(String values) {
            return new TextSectionWithTitle(Arrays.asList(values.split("\\n")));
        }
    },
    ORGANIZATION {
        @Override
        public String toHtml(Section section, SectionType type) {
            return section.toString();
        }

        @Override
        public Section createSection(String value) {
            throw new UnsupportedOperationException();
        }
    };

    public abstract String toHtml(Section section, SectionType type);

    public abstract Section createSection(String value);
}
