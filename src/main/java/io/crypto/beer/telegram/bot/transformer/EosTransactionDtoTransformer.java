package io.crypto.beer.telegram.bot.transformer;

import java.util.List;

import io.crypto.beer.telegram.bot.integration.eos.dto.Action;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EosTransactionDtoTransformer {

	/**
	 * This method gets list of new transactions, process them, and returns cleaned
	 * ones.
	 *
	 * @param actions           list of all new actions from server, represented as
	 *                          3 actions per 1 actual transaction
	 * @param lastTransactionId the lastTransactionId of last processed transaction
	 *                          by server
	 * @return list of EosTransactionDto`s
	 */
	public static final String transform(List<Action> actions) {

		String transactions = "";

		for (int i = 0; i < actions.size(); i += 3) {
			Action action = actions.get(i);
			transactions += "\n\n";
			transactions += "*Date:* " + action.getCreatedAt() + "\n";
			transactions += "*From:* " + action.getAct().getData().getFrom() + "\n";
			transactions += "*To:* " + action.getAct().getData().getTo() + "\n";
			transactions += "*Quantity:* "
					+ (action.getAct().getData().getQuantity().contains("JUNGLE") ? 100.0000
							: EosTransformer.eosBalanceTransformWithoutBraces(action.getAct().getData().getQuantity()))
					+ "\n";
			transactions += "*Memo:* " + action.getAct().getData().getMemo() + "\n";
		}

		log.info("TXS: {}", transactions);
		return transactions;
	}
}
