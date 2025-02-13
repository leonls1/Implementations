package LeonLederhos.improvements.model.dto.response;

import java.util.List;

public record AuctionRankingTopResponse(
    List<BidResponseDto> bids
) {

}
