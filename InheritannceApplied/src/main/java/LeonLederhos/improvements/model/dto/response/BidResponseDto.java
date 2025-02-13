package LeonLederhos.improvements.model.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record BidResponseDto (

        BigDecimal amount,
        LocalDateTime bidDateTime,
        UserResponse user


){

        }

    


