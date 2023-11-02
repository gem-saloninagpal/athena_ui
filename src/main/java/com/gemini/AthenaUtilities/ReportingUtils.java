package com.gemini.AthenaUtilities;

public class ReportingUtils {
    public enum Report {

        PASS_TEST_STATUS("Status code verified as expected : "),
        PASS_TEST_RESPONSE("Data verified successfully with api response as expected: "),
        TRENDING_TITLE_VERIFY("Verify the presence of Trending Section elements "),
        LOGGER_EXCEPTION("An Exception occurred ");
        final String message;

        Report(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

    }
}
