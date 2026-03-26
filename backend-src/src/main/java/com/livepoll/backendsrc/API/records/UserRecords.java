package com.livepoll.backendsrc.API.records;

public class UserRecords
{
    //Generic Response
    public record ApiResponse<T>(
            boolean successful,
            String message,
            T data
    ){
        //Successful Response
        public static <T> ApiResponse<T> success (String message, T data){
            return new ApiResponse<>(true, message, data);
        }
        public static <T> ApiResponse<T> success (String message){
            return new ApiResponse<>(true, message, null);
        }

        //Unsuccessful Response
        public static <T> ApiResponse<T> error (String message, T data){
            return new ApiResponse<>(false, message, data);
        }
    }

    //Specific Responses
    public record SuccesfullAuthResponseRecord(
            String JWTToken,
            String email
    ){}
    public record ErrorResponseRecord(
            String errorCode
    ){}


    public record CredentialsSubmitRequestRecord(
            String email,
            String password
    ){}
}

