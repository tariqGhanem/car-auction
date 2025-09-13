package com.auction.bidding.Repo;

import com.auction.bidding.Models.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

}

