package com.example.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Controller
public class EndpointLander {

    @GetMapping(value = "/health")
    public ResponseEntity<?> display(@RequestParam(value = "format") String input) throws Exception{

        if(!StringUtils.isEmpty(input)){
            if(input.equalsIgnoreCase("part")){
                outputPart res = new outputPart();
                res.setStatus("OK");
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(res);
                return ResponseEntity.status(HttpStatus.OK).body(json);
            }else if(input.equalsIgnoreCase("full")){
                outputFull res = new outputFull();
                res.setStatus("OK");
                ZoneId zoneId = ZoneId.of("America/Los_Angeles");
                res.setLt(ZonedDateTime.now(zoneId));
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(res);
                return ResponseEntity.status(HttpStatus.OK).body(json);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
  class outputPart{
        private String status;

      public String getStatus() {
          return status;
      }
      public void setStatus(String inputs) {
          this.status = inputs;
      }
  }

    class outputFull{
        private String status;
        private ZonedDateTime lt;

        public ZonedDateTime getLt() {
            return lt;
        }

        public void setLt(ZonedDateTime lt) {
            this.lt = lt;
        }
        public String getStatus() {
            return status;
        }
        public void setStatus(String inputs) {
            this.status = inputs;
        }
    }
}
