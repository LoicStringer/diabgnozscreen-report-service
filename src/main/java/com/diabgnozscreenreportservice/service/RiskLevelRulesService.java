package com.diabgnozscreenreportservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.diabgnozscreenreportservice.utility.RiskLevelEnum;

@Service
public class RiskLevelRulesService {

	@Value("${criticalPatient.age}")
	private int criticalAge;

	@Autowired
	private MessageSource messageSource;

	public RiskLevelEnum evaluateDiabetesRiskLevelForWomen(int patientAge, List<String> patientHistoryContent) {

		List<RiskLevelEnum> riskLevelResultsList = new ArrayList<RiskLevelEnum>();
		riskLevelResultsList.add(evaluateNoneRiskLevel(patientHistoryContent));
		riskLevelResultsList.add(evaluateBorderLineRiskLevel(patientHistoryContent, patientAge));
		riskLevelResultsList
				.add(evaluateInDangerRiskLevelForAnyPatientAboveCriticalAge(patientHistoryContent, patientAge));
		riskLevelResultsList.add(evaluateInDangerRiskLevelForWomenUnderCriticalAge(patientHistoryContent, patientAge));
		riskLevelResultsList
				.add(evaluateEarlyOnsetRiskLevelForAnyPatientAboveCriticalAge(patientHistoryContent, patientAge));
		riskLevelResultsList
				.add(evaluateEarlyOnsetRiskLevelForWomenUnderCriticalAge(patientHistoryContent, patientAge));

		RiskLevelEnum result = riskLevelResultsList.stream().filter(r -> r != null).findFirst()
				.orElse(RiskLevelEnum.UNDEFINED);

		return result;
	}

	public RiskLevelEnum evaluateDiabetesRiskLevelForMen(int patientAge, List<String> patientHistoryContent) {

		List<RiskLevelEnum> riskLevelResultsList = new ArrayList<RiskLevelEnum>();
		riskLevelResultsList.add(evaluateNoneRiskLevel(patientHistoryContent));
		riskLevelResultsList.add(evaluateBorderLineRiskLevel(patientHistoryContent, patientAge));
		riskLevelResultsList
				.add(evaluateInDangerRiskLevelForAnyPatientAboveCriticalAge(patientHistoryContent, patientAge));
		riskLevelResultsList.add(evaluateInDangerRiskLevelForMenUnderCriticalAge(patientHistoryContent, patientAge));
		riskLevelResultsList
				.add(evaluateEarlyOnsetRiskLevelForAnyPatientAboveCriticalAge(patientHistoryContent, patientAge));
		riskLevelResultsList.add(evaluateEarlyOnsetRiskLevelForMenUnderCriticalAge(patientHistoryContent, patientAge));

		RiskLevelEnum result = riskLevelResultsList.stream().filter(r -> r != null).findFirst().get();

		return result;
	}

	private RiskLevelEnum evaluateNoneRiskLevel(List<String> patientHistoryContent) {
		RiskLevelEnum result = null;
		if (countMatchingKeywords(patientHistoryContent) < 2)
			result = RiskLevelEnum.NONE;
		return result;
	}

	private RiskLevelEnum evaluateBorderLineRiskLevel(List<String> patientHistoryContent, int patientAge) {
		RiskLevelEnum result = null;
		if (evaluateCriticalAge(patientAge) && countMatchingKeywords(patientHistoryContent) >= 2
				&& countMatchingKeywords(patientHistoryContent) < 6)
			result = RiskLevelEnum.BORDERLINE;
		return result;
	}

	private RiskLevelEnum evaluateInDangerRiskLevelForAnyPatientAboveCriticalAge(List<String> patientHistoryContent,
			int patientAge) {
		RiskLevelEnum result = null;
		if (evaluateCriticalAge(patientAge) && countMatchingKeywords(patientHistoryContent) >= 6 
				&& countMatchingKeywords(patientHistoryContent) < 8)
			result = RiskLevelEnum.IN_DANGER;
		return result;
	}

	private RiskLevelEnum evaluateInDangerRiskLevelForWomenUnderCriticalAge(List<String> patientHistoryContent,
			int patientAge) {
		RiskLevelEnum result = null;
		if (!evaluateCriticalAge(patientAge) && countMatchingKeywords(patientHistoryContent) >= 4
				&& countMatchingKeywords(patientHistoryContent) < 7)
			result = RiskLevelEnum.IN_DANGER;
		return result;
	}

	private RiskLevelEnum evaluateInDangerRiskLevelForMenUnderCriticalAge(List<String> patientHistoryContent,
			int patientAge) {
		RiskLevelEnum result = null;
		if (!evaluateCriticalAge(patientAge) && countMatchingKeywords(patientHistoryContent) >= 3
				&& countMatchingKeywords(patientHistoryContent) < 5)
			result = RiskLevelEnum.IN_DANGER;
		return result;
	}

	private RiskLevelEnum evaluateEarlyOnsetRiskLevelForAnyPatientAboveCriticalAge(List<String> patientHistoryContent,
			int patientAge) {
		RiskLevelEnum result = null;
		if (evaluateCriticalAge(patientAge) && countMatchingKeywords(patientHistoryContent) >= 8)
			result = RiskLevelEnum.EARLY_ONSET;
		return result;
	}

	private RiskLevelEnum evaluateEarlyOnsetRiskLevelForWomenUnderCriticalAge(List<String> patientHistoryContent,
			int patientAge) {
		RiskLevelEnum result = null;
		if (!evaluateCriticalAge(patientAge) && countMatchingKeywords(patientHistoryContent) >= 7)
			result = RiskLevelEnum.EARLY_ONSET;
		return result;
	}

	private RiskLevelEnum evaluateEarlyOnsetRiskLevelForMenUnderCriticalAge(List<String> patientHistoryContent,
			int patientAge) {
		RiskLevelEnum result = null;
		if (!evaluateCriticalAge(patientAge) && countMatchingKeywords(patientHistoryContent) >= 5)
			result = RiskLevelEnum.EARLY_ONSET;
		return result;
	}

	private boolean evaluateCriticalAge(int patientAge) {
		return (patientAge > criticalAge);
	}

	private int countMatchingKeywords(List<String> patientHistoryContent) {
		List<String> keywordsList = initKeywordsList();
		List<String> matchingKeywordsListWithDuplicates = new ArrayList<String>();
		patientHistoryContent.stream().forEach(nc -> {
			matchingKeywordsListWithDuplicates.addAll(matchingKeywordsListInString(nc, keywordsList));
		});

		if (matchingKeywordsListWithDuplicates.isEmpty())
			return 0;

		List<String> matchingKeywordsList = matchingKeywordsListWithDuplicates.stream().distinct()
				.collect(Collectors.toList());

		return matchingKeywordsList.size();
	}

	private List<String> matchingKeywordsListInString(String noteContent, List<String> keywordsList) {
		List<String> matchingKeyWordsList = keywordsList.stream()
				.filter(kw -> noteContent.toLowerCase().contains(kw.toLowerCase()) == true).distinct()
				.collect(Collectors.toList());
		return matchingKeyWordsList;
	}

	private List<String> initKeywordsList() {
		String keywordsStringChain = messageSource.getMessage("keywords.list", null, LocaleContextHolder.getLocale());
		List<String> keywordsList = Arrays.asList(keywordsStringChain.split(","));
		return keywordsList;
	}

}
