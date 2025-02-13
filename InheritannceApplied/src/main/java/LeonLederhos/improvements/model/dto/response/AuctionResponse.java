package LeonLederhos.improvements.model.dto.response;

import com.noCountry.artAuction.model.enums.AuctionState;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AuctionResponse(
        Long id,
        @Schema(description = "datetime whe the auction starts")
        LocalDateTime auctionStart,
        ArtWorkResponse artWork,
        @Schema(description = "the moment when the auction change state to close")
        LocalDateTime auctionEnd,
        @Schema(description = "the  base amount where the auction starts")
        BigDecimal baseAmount,
        @Schema(description = " the minim amount the auction needs to increase for a bid")
        BigDecimal incrementStep,
        AuctionState state,
        AuctionRankingResponse auctionRanking
       
      ) {
        
}
