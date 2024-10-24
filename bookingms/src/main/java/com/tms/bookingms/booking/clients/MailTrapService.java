package com.tms.bookingms.booking.clients;

import java.io.IOException;

import org.springframework.stereotype.Service;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class MailTrapService {

    private static final String API_URL = "https://sandbox.api.mailtrap.io/api/send/3232584";
    private static final String API_TOKEN = "Bearer 509bbcaa6a9f8a039efbe8819163369b";  // Your Mailtrap API Token

    public void sendOtpEmail(String to, Long otp) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");

        String bodyContent = "{\"from\":{\"email\":\"hello@example.com\",\"name\":\"Booking OTP\"},"
                + "\"to\":[{\"email\":\"" + to + "\"}],"
                + "\"subject\":\"Your OTP for Booking\","
                + "\"text\":\"Your OTP is: " + otp + "\"}";

        RequestBody body = RequestBody.create(mediaType, bodyContent);
        Request request = new Request.Builder()
            .url(API_URL)
            .method("POST", body)
            .addHeader("Authorization", API_TOKEN)
            .addHeader("Content-Type", "application/json")
            .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Failed to send OTP email: " + response.message());
        }
    }
}
