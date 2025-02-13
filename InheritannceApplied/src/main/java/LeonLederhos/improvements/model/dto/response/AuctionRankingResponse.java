package LeonLederhos.improvements.model.dto.response;

import java.util.List;

public record AuctionRankingResponse(
        Long id,
        List<BidResponseDto> bids

) {
}
