package br.com.tpa.java8.capitulos;

import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.tpa.java8.model.Customer;
import br.com.tpa.java8.model.Payment;
import br.com.tpa.java8.model.Product;
import br.com.tpa.java8.model.Subscription;

public class Capitulo11 {
	
	public static void main(String[] args) {
		
		Customer thais = new Customer("Thaís");
		Customer ana = new Customer("Ana");
		Customer renato = new Customer("Renato");
		Customer thiago = new Customer("Thiago");
		
		Product bach = new Product("Bach Completo", Paths.get("/music/bach.mp3"), new BigDecimal(100));
		Product poderosas = new Product("Poderosas Anita", Paths.get("/music/poderosas.mp3"), new BigDecimal(90));
		Product bandeira = new Product("Bandeira Brasil", Paths.get("/images/brasil.jpg"), new BigDecimal(50));
		Product beauty = new Product("Beleza Americana", Paths.get("beauty.mov"), new BigDecimal(150));
		Product vingadores = new Product("Os Vingadores", Paths.get("/movies/vingadores.mov"), new BigDecimal(200));
		Product amelie = new Product("Amelie Poulain", Paths.get("/movies/amelie.mov"), new BigDecimal(100));
		
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = today.minusDays(1);
		LocalDateTime lastMonth = today.minusMonths(1);
		
		Payment payment1 = new Payment(asList(bach, poderosas), today, thais);
		Payment payment2 = new Payment(asList(bach, bandeira, amelie), yesterday, ana);
		Payment payment3 = new Payment(asList(bach, beauty, vingadores), today, renato);
		Payment payment4 = new Payment(asList(bach, poderosas, amelie), lastMonth, thiago);
		Payment payment5 = new Payment(asList(beauty, amelie), yesterday, thais);
		
		List<Payment> payments = asList(payment1, payment2, payment3, payment4, payment5);
		
		//ordenar os pagamentos por data e imprimi-los
		payments.stream()
			.sorted(Comparator.comparing(Payment::getDate))
			.forEach(System.out::println);
		
		//calcular o valor total do pagamento payment1 e imprimi-lo
		payment1.getProducts().stream()
			.map(Product::getPrice)
			.reduce(BigDecimal::add)
			.ifPresent(System.out::println);
			
		//somar todos os valores de todos os pagamentos
		BigDecimal totalFlat = 
				payments.stream()
				.flatMap(p -> p.getProducts().stream().map(Product::getPrice))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		System.out.println(totalFlat);
		
		//obter produtos mais vendidos -p 104
		//lambda
		Stream<Product> products = payments.stream()
				.map(Payment::getProducts)
				.flatMap(p -> p.stream());
		
		//method reference
		Stream<Product> products2 = payments.stream()
				.map(Payment::getProducts)
				.flatMap(List::stream);
		
		//em apenas um map
		Stream<Product> products3 = payments.stream()
				.flatMap(p -> p.getProducts().stream());
		
		//total agrupado por produto
		Map<Product, Long> topProducts = payments.stream()
				.flatMap(p -> p.getProducts().stream())
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.counting()));
		
		topProducts.entrySet().stream()
			.forEach(System.out::println);
		
		//obter o mais vendido
		topProducts.entrySet().stream()
			.max(Comparator.comparing(Map.Entry::getValue))
			.ifPresent(System.out::println);
		
		//obter a soma dos valores por produto
		Map<Product, BigDecimal> totalValuePerProduct = payments.stream()
				.flatMap(p -> p.getProducts().stream())
				.collect(Collectors.groupingBy(Function.identity(),
						 Collectors.reducing(BigDecimal.ZERO, Product::getPrice, 
								 BigDecimal::add)));
		
		totalValuePerProduct.entrySet().stream()
			.forEach(System.out::println);
		
		//produtos agrupados por cliente
		Map<Customer, List<Payment>> customerToPayments = payments.stream()
				.collect(Collectors.groupingBy(Payment::getCustomer));
		
		Map<Customer, List<List<Product>>> customerToProductsList = 
				payments.stream()
				.collect(Collectors.groupingBy(Payment::getCustomer, 
						Collectors.mapping(Payment::getProducts, Collectors.toList())));
		
		Map<Customer, List<Product>> customerToProducts2Steps = 
				customerToProductsList.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, 
						e -> e.getValue().stream()
						.flatMap(List::stream)
						.collect(Collectors.toList())));
		
		customerToProducts2Steps.entrySet().stream()
			.sorted(Comparator.comparing(e -> e.getKey().getName()))
			.forEach(System.out::println);
		
		//total por cliente
		Map<Customer, BigDecimal> totalValuePerCustomer = payments.stream()
				.collect(Collectors.groupingBy(Payment::getCustomer,
						 Collectors.reducing(BigDecimal.ZERO,
						 p -> p.getProducts().stream().map(Product::getPrice).reduce(
								 BigDecimal.ZERO, BigDecimal::add),
						 		BigDecimal::add)));
		
		System.out.println(totalValuePerCustomer);
		
		//criação de variável temporária para melhorar a legibilidade
		Function<Payment, BigDecimal> paymentToTotal = 
				p -> p.getProducts().stream()
				.map(Product::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
				
		//inclusão da variável paymentToTotal no reducing
				Map<Customer, BigDecimal> totalValuePerCustomer2 = payments.stream()
						.collect(Collectors.groupingBy(Payment::getCustomer,
								 Collectors.reducing(BigDecimal.ZERO,
										paymentToTotal,
								 		BigDecimal::add)));
		
		//imprime o mesmo resultado da totalValuePerCustomer
		System.out.println(totalValuePerCustomer2);
		
		//imprime ordenadamente
		totalValuePerCustomer2.entrySet().stream()
			.sorted(Comparator.comparing(Map.Entry::getValue))
			.forEach(System.out::println);
			
		//agrupar os pagamentos por mês
		Map<YearMonth, List<Payment>> paymentsPerMonth = payments.stream()
				.collect(Collectors.groupingBy(p -> YearMonth.from(p.getDate())));
		
		paymentsPerMonth.entrySet().stream()
			.forEach(System.out::println);
		
		//obter o faturamento da loja por mês
		Map<YearMonth, BigDecimal> paymentsValuePerMonth = payments.stream()
				.collect(Collectors.groupingBy(p -> YearMonth.from(p.getDate()),
						Collectors.reducing(BigDecimal.ZERO,
								p -> p.getProducts().stream()
								.map(Product::getPrice)
								.reduce(BigDecimal.ZERO,
										BigDecimal::add),
								BigDecimal::add)));

		//
		BigDecimal monthlyFee = new BigDecimal("99.90");
		Subscription s1 = new Subscription(monthlyFee, yesterday.minusMonths(5), thais);
		Subscription s2 = new Subscription(monthlyFee, yesterday.minusMonths(8), today.minusMonths(1), ana);
		Subscription s3 = new Subscription(monthlyFee, yesterday.minusMonths(5), renato);
		
		List<Subscription> subscriptions = Arrays.asList(s1, s2, s3);
		
		//calcular quantos meses foram pagos
		long meses = ChronoUnit.MONTHS
				.between(s1.getBegin(), LocalDateTime.now());
		
		//verificar se a assinatura terminou
		long meses2 = ChronoUnit.MONTHS
				.between(s1.getBegin(), s1.getEnd().orElse(LocalDateTime.now()));
		
		//calcular o valor gerado pela assinatura
		BigDecimal total = s1.getMonthlyFee()
							.multiply(new BigDecimal(ChronoUnit.MONTHS
									.between(s1.getBegin(),
											s1.getEnd().orElse(LocalDateTime.now()))));
		
		//somar todo o total pago
		BigDecimal totalPaid = subscriptions.stream()
				.map(Subscription::getTotalPaid)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		
		
		
	}

}
