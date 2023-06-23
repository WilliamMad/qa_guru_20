package qaguru.demoqa.tests.HW9;

import qaguru.demoqa.tests.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedTests extends BaseTest {

    @BeforeEach
    void openPage() {
        open("/books");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }


    @DisplayName("Поисковая выдача должна выдавать корректного автора книги при поиске по наименованию книги")
    @CsvSource(value = {
            "Git Pocket Guide |  Richard E. Silverman",
            "Speaking JavaScript    |  Axel Rauschmayer"
    },
            delimiter = '|')
    @ParameterizedTest
    void searchResultShouldContainAuthorName(String bookName, String authorName) {
        $("#searchBox").setValue(bookName);
        $("[class='rt-tbody']").shouldHave(text(authorName));
    }


    @ValueSource(
            strings = {"How to cook", "Hunting for newbies"}
    )
    @DisplayName("Поисковая выдача должна содержать текст 'No rows found' при некорректном поиске")
    @ParameterizedTest
    void searchResultsShouldNotBeEmpty(String bookName) {
        $("#searchBox").setValue(bookName);
        $("[class='rt-noData']").shouldHave(text("No rows found"));
    }


    static Stream<Arguments> multipleResults() {
        return Stream.of(
                Arguments.of("Git Pocket Guide",
                        "Learning JavaScript Design Patterns",
                        "Designing Evolvable Web APIs with ASP.NET",
                        "Speaking JavaScript",
                        "You Don't Know JS",
                        "Programming JavaScript Applications")
        );
    }
    @DisplayName("Поиск должен выдавать все книги, соответствующие издательству")
    @MethodSource("multipleResults")
    @ParameterizedTest
    void searchResultShouldContainMultipleResults(String bookNames) {
        $("#searchBox").setValue("O'Reilly Media");
        $("[class='rt-tbody'] div").shouldHave(text(bookNames));
    }
}
