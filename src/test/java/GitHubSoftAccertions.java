import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GitHubSoftAccertions {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
    }

    @Test
    void checkGithubWikiPage() {
        open("/selenide/selenide");
        $("#repository-container-header").shouldHave(text("selenide / selenide"));
        $("#wiki-tab").click();
        $("#user-content-welcome-to-the-selenide-wiki").shouldHave(text("Welcome to the selenide wiki!"));
        $("#wiki-pages-filter").setValue("Softassertions").pressEnter();
        $("a[href='/selenide/selenide/wiki/SoftAssertions']").click();
        $(".gh-header-title").shouldHave(text("SoftAssertions"));
        $("#wiki-body").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));
    }
}
