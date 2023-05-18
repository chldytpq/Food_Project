package kopo.poly.service;

import kopo.poly.dto.BorderDto;
import kopo.poly.entity.Border;
import kopo.poly.repository.BorderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j

public class BorderService {

    private final BorderRepository borderRepository;

    @Transactional
    public List<BorderDto> getborderList(){

        log.info(this.getClass().getName() + "getborderList Start!");

        List<Border> borders = borderRepository.findAll();
        List<BorderDto> borderdtoList = new ArrayList<>();

        for(Border border : borders){
            BorderDto borderdto = BorderDto.builder()
                    .id(border.getId())
                    .memberEmail(border.getMemberEmail())
                    .title(border.getTitle())
                    .content(border.getContent())
                    .build();

            borderdtoList.add(borderdto);
        }

        log.info(this.getClass().getName() + "getborderList Stop!");

        return borderdtoList;
    }

    public Border saveBorder(Border border){

        log.info(this.getClass().getName() + "saveBorder Start!");

        log.info(this.getClass().getName() + "saveBorder Stop!");

        return borderRepository.save(border);
    }

    public BorderDto getborderDetail(Long getid){

        log.info(this.getClass().getName() + "getborderDetail Start!");

        Optional<Border> border = borderRepository.findById(getid);

        BorderDto borderdto = BorderDto.builder()
                .id(border.get().getId())
                .memberEmail(border.get().getMemberEmail())
                .title(border.get().getTitle())
                .content(border.get().getContent())
                .build();

        log.info(this.getClass().getName() + "getborderDetail Stop!");

        return borderdto;
    }



}
