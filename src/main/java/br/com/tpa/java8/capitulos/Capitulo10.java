package br.com.tpa.java8.capitulos;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Locale;

public class Capitulo10 {
	
	public static void main(String[] args) {
		//nova API de datas
		
		//criar uma data com um m�s a partir da data atual (old)
		Calendar mesQueVem = Calendar.getInstance();
		mesQueVem.add(Calendar.MONTH, 1);
		
		//criar uma data com um m�s a partir da data atual (Java 8)
		LocalDate mesQueVem2 = LocalDate.now().plusMonths(1);
		
		//Data e hora
		LocalDateTime agora = LocalDateTime.now();
		System.out.println(agora);
		
		//apenas o hor�rio local
		LocalTime horario = LocalTime.now();
		System.out.println(horario);
		
		//LocalDateTime com hor�rio espec�fico
		LocalDateTime hojeAoMeioDia = LocalDate.now().atTime(12,0);
		
		LocalDate hoje = LocalDate.now();
		LocalDateTime dataEhora = hoje.atTime(horario);
		
		//data com hora e timezone
		ZonedDateTime dataComHoraETimeZone = dataEhora.atZone(ZoneId.of("America/Sao_Paulo"));
		
		//modificar o ano de uma data
		LocalDate dataDoPassado = LocalDate.now().withYear(1988);
		System.out.println(dataDoPassado);
		
		//verificar se uma medida de tempo acontece antes, depois ou ao mesmo tempo que outra
		LocalDate amanha = LocalDate.now().plusDays(1);
		System.out.println(hoje.isBefore(amanha)); //apenas esse retorna true
		System.out.println(hoje.isAfter(amanha));
		System.out.println(hoje.isEqual(amanha));
		
		//obter o dia do m�s atual
		System.out.println("hoje � dia: " + MonthDay.now().getDayOfMonth());
		
		//obter o m�s e o ano de uma data
		YearMonth ym = YearMonth.from(LocalDate.now());
		System.out.println(ym.getMonth() + " " + ym.getYear());
		
		//usar o enum com os meses do ano � uma boa pr�tica
		System.out.println(LocalDate.of(2014, Month.DECEMBER, 25));
		System.out.println(Month.DECEMBER.firstMonthOfQuarter());
		System.out.println(Month.DECEMBER.plus(2));
		System.out.println(Month.DECEMBER.minus(1));
		
		//imprimir o nome do m�s em portugu�s
		Locale pt = new Locale("pt");
		System.out.println(Month.DECEMBER.getDisplayName(TextStyle.FULL, pt)); //nome completo
		System.out.println(Month.DECEMBER.getDisplayName(TextStyle.SHORT, pt)); //nome abreviado
		
		//formatar data em horas
		System.out.println(dataEhora.format(DateTimeFormatter.ISO_LOCAL_TIME));
		
		//definir um padr�o de formata��o
		LocalDateTime agora2 = LocalDateTime.now();
		agora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); 
		System.out.println(agora2); //n�o formatou
		
		//obter a diferen�a entre duas datas
		LocalDate data = LocalDate.now();
		LocalDate outraData = LocalDate.of(1988, Month.OCTOBER, 31);
		System.out.println(ChronoUnit.DAYS.between(outraData, data));
		
		//obter a quantidade de anos, meses e dias entre duas datas
		Period periodo = Period.between(outraData, data);
		System.out.printf("%s anos, %s meses, %s dias", periodo.getYears(), periodo.getMonths(), periodo.getDays());
		
		//obter a quantidade de horas, minutos e segundo entre dois hor�rios
		LocalDateTime dataAtual = LocalDateTime.now();
		LocalDateTime daquiAUmaHora = LocalDateTime.now().plusHours(1);
		Duration duration = Duration.between(dataAtual, daquiAUmaHora);
		
		if(duration.isNegative()) {
			duration = duration.negated();
		}
		
		System.out.printf("%s horas, %s minutos, %s segundos", duration.toHours(), duration.toMinutes(), duration.toHours());
		
	}

}
