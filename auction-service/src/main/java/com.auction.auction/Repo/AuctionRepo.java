package com.auction.auction.Repo;

import com.auction.auction.Models.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuctionRepo extends JpaRepository<Auction, Long>, JpaSpecificationExecutor<Auction> {
}
