package main.project.server.heart.controller;

import lombok.RequiredArgsConstructor;
import main.project.server.dto.SingleResponseDto;
import main.project.server.heart.service.HeartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.security.Principal;

@Validated
@RestController
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;


    // 찜하기
    @PostMapping("/api/auth/guesthouse/{guesthouse-id}/heart-on")
    public ResponseEntity postHeartOn(@PathVariable("guesthouse-id") @Positive Long guestHouseId, Principal principal){

        String memberId = principal.getName();
        String response = heartService.clickEmptyHeart(memberId, guestHouseId);
        Boolean heartStatus = heartService.heartStatus(memberId, guestHouseId);

        HttpStatus httpStatus;
        if(response.equals("Duplicate Heart On")) httpStatus = HttpStatus.NOT_ACCEPTABLE;
        else httpStatus = HttpStatus.OK;

        return new ResponseEntity<>(new SingleResponseDto<>(response, heartStatus), httpStatus);
    }

    // 찜하기 취소
    @PostMapping("/api/auth/guesthouse/{guesthouse-id}/heart-off")
    public ResponseEntity postHeartOff(@PathVariable("guesthouse-id") @Positive Long guestHouseId, Principal principal) {

        String memberId = principal.getName();
        String response = heartService.clickFullHeart(memberId, guestHouseId);
        Boolean heartStatus = heartService.heartStatus(memberId, guestHouseId);

        HttpStatus httpStatus;
        if(response.equals("Duplicate Heart Off")) httpStatus = HttpStatus.NOT_ACCEPTABLE;
        else httpStatus = HttpStatus.OK;

        return new ResponseEntity<>(new SingleResponseDto<>(response, heartStatus), httpStatus);
    }

    // 찜하기 상태
    @GetMapping("/api/auth/guesthouse/{guesthouse-id}/heart")
    public ResponseEntity getHeart(@PathVariable("guesthouse-id") @Positive Long guestHouseId, Principal principal) {

        String memberId = principal.getName();
        Boolean heartStatus = heartService.heartStatus(memberId, guestHouseId);

        return new ResponseEntity<>(new SingleResponseDto<>("get ok", heartStatus), HttpStatus.OK);
    }
}
