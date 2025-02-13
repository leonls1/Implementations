package LeonLederhos.improvements.model.dto.response;

import com.noCountry.artAuction.model.enums.ArtCategory;
import com.noCountry.artAuction.model.enums.AuctionState;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AuctionCollectionResponse(
        AuctionState state,
        String artWorkDescription,
        String artWorkImage,
        String artWorkName,
        LocalDateTime auctionStart,
        LocalDateTime auctionEnd,
        BigDecimal baseAmount,
        Long userId,
        Long auctionId,
        ArtCategory category
        ) {
}
