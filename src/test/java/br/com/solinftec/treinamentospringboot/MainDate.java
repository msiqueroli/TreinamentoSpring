package br.com.solinftec.treinamentospringboot;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MainDate {

    public static void main(String[] args) throws ParseException {

        //https://www.devmedia.com.br/como-manipular-datas-com-o-java-8/3413
        String data = "08:40:37 09-02-2022";
        Date horaAtual = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss dd-MM-yyyy");
        Date date = formatter.parse(data);

        LocalDateTime localDateTimeAtual = LocalDateTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Timestamp timestamp = new Timestamp(new Date().getTime());

        System.out.println(timestamp);
        System.out.println(dateTimeFormatter.format(LocalDate.now()));
        System.out.println(LocalDate.now());

    }

}
