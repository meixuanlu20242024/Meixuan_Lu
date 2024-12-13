package com.insurance.system.shared.domain.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SmsResponse {
    @JsonProperty("status")
    private Status status;

    @JsonProperty("sms-response-details")
    private SmsResponseDetails[] smsResponseDetails;

    @Data
    public static class Status {
        @JsonProperty("error-code")
        private String errorCode;

        @JsonProperty("error-status")
        private String errorStatus;

        @JsonProperty("error-description")
        private String errorDescription;
    }

    @Data
    public static class SmsResponseDetails {
        @JsonProperty("success-count")
        private String successCount;

        @JsonProperty("failed-sms-details")
        private FailedSmsDetails[] failedSmsDetails;

        @JsonProperty("sent-sms-details")
        private SentSmsDetails[] sentSmsDetails;
    }

    @Data
    public static class FailedSmsDetails {
        private String count;
        private Reason[] reasons;

        @Data
        public static class Reason {
            @JsonProperty("sms-client-id")
            private String smsClientId;

            @JsonProperty("mobile-no")
            private String mobileNo;

            @JsonProperty("failed-reason")
            private String failedReason;

            @JsonProperty("message-content")
            private String messageContent;
        }
    }

    @Data
    public static class SentSmsDetails {
        @JsonProperty("sms-client-id")
        private String smsClientId;

        @JsonProperty("message-id")
        private String messageId;

        @JsonProperty("mobile-no")
        private String mobileNo;
    }
}