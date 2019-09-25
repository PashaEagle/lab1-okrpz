package io.crypto.beer.telegram.bot.integration.eos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class KeyGenerator {

	private List<String> availableKeys = new ArrayList<>();
	
	public KeyGenerator() {
		availableKeys.add("EOS8kgx36DxDCzTgwGWGe256ktL9WBHxUy2EaCgQgDfdUuq1Q1BB2");
		availableKeys.add("EOS5n6Su1K9BomybMNV1Sr3gzU9AZmiQY5o6Yx2u3L9WS2rZ3Q9Zx");
		availableKeys.add("EOS5k87yEgbm5RhigW23EZweAAeoTL9iY2io8fSdTX5g4WRRXYHQF");
		availableKeys.add("EOS5SRv7ptp63yrvbj2HXv9pM8e74V6obk4Wf2pRz4oUD4qzi1RZo");
		availableKeys.add("EOS7EM2WMPyVXKnZh5r6H6iwq6Q9QetFmGsAE9dUgbagorcVUEdTf");
		availableKeys.add("EOS5kpSMpYacLmwuG3fM7g7HJqAtW2e8MtnwHcFuvWpJSeeFA8PtA");
		availableKeys.add("EOS85fieYGAfLqroDdanXMDveX7yiF6QhJ9XmCLzhusBHJrLVXTDW");
		availableKeys.add("EOS7Jem1YzpytHuEoKsPHhmBbkF5mn1E5ko88dEV35bFYgPAd3XCr");
		availableKeys.add("EOS6pPZJCcGvgaWymM8FeaMZMECgACfP5KHUcnroyAKcDTegNEWrS");
		availableKeys.add("EOS87RVixJ4qngXwL7hFW3hJcxU9kdmTvtJmoDFTaQdCAkZ4Du6Tu");
		availableKeys.add("EOS7fpxqAKcXCaq5qmAWp3enYhLEZUCoSZC64N6ASXEgL6xdPPDJE");
		availableKeys.add("EOS7NM6onf41a4oTtxc3ULvRqChYVPSZrV4Ry2p8rKV5RVTFgMCVQ");
		availableKeys.add("EOS6er8miZaP6isyQacvVqrruQTaXAi2j9NSA1WnkmohXwEHkkMKy");
		availableKeys.add("EOS6DzEDUGrQSLuS6eM2WAY2fg5CZoDjPhhvCMiohMfUvBo9p6bvd");
	}
	
	public String generateNewKey() {
		Random random = new Random();
		return availableKeys.get(random.nextInt(availableKeys.size()));
	}
}
