//@formatter:off
/**
 * 
 *       . * .
 *     * RRRR  *    Copyright (c) 2016 OHIM: Office for Harmonization
 *   .   RR  R   .  in the Internal Market (trade marks and designs)
 *   *   RRR     *
 *    .  RR RR  .   ALL RIGHTS RESERVED
 *     * . _ . *
 * 
 * 	Author: SetforIT
 * 	Modification Date: 18-ago-2016 11:59:33
 * 	SVN Revision Number: $Revision$
 * 
 */
//@formatter:on
package com.solutions.mappings;

/**
 * The Enum Variable.
 */
public enum Variable {
	//@formatter:off

	// Activiti
	ASSIGNEE_CUSTOMTASK("examiner"),
	CUSTOM_TASK("CUSTOM_TASK"),
	CUSTOM_EXECUTION("CUSTOM_EXECUTION"),
	CUSTOMTASK_FLAG("isCustomTask"),
	CUSTOMTASK_TASKKEY("taskKey"),
	CUSTOMTASK_NAME("customtaskName"),

	// Diagrams
	AUTOMATIC_EXAMINATION_RESULT("automatic_examination_result"),
	CALCULATE_CREATE_FEES("calculateAndCreateFee_fees"),
	CALCULATE_CREATE_FEES_RESULT("calculateAndCreateFee_result"),
	CHECK_DOC_AUTO_RESULT("checkDocAutomatically_result"),
	CHECK_FEES_AND_PAYMENT("checkFeesAndPayments_result"),
	CHECK_GOODS_AND_SERVICES_FORMAT_RESULT("check_goods_and_services_format_result"),
	CHECK_LEGAL_STATUS("checkLegalStatus_legalStatus"),
	CHECK_REFUND_FEES("checkRefundFees_feeRefund"),
	CHECK_REPROGRAPHY("checkReprography_result"),
	CHECK_TRANSLATE_REQ_EXISTENCES("check_translate_request_existences"),
	DEBIT_FEE_RESULT("debitFee_result"),
	DELETE_TEMPORAL_DOCUMENT("deleteTemporalDocument_result"),
	EXAMINE_MANUAL_INSP_REQUEST_RESULT("IOF_EXAMINE_INSPECTION_REQUEST_RESULT"),
	EX_ANTE_CHECKS("ex_ante_checks"),
	FEE_REQUIRED("fee_required"),
	FEE_VALIDATION_RESULT("fee_validation_result"),
	GENERATE_LETTER_RESULT("generateLetter_result"),
	GET_DOSSIER_DETAILS_DOSSIER("getDossierDetails_dossier"),
	GS_TRANSLATION_CHECKS("gs_translation_checks"),
	IMPACT_ON_GOODS_AND_SERVICES("impact_on_goods_and_services"),
	IRREGULARITIES("checkIrregularities_irregularities"),
	IS_IPRIGHT_CORRECT_STATUS("is_ipright_correct_status"),
	IS_RECALCULATE_FEE_REQUIRED("recalculate_fee_required"),
	LACK_OF_FUNDS_RESULT("lackOfFunds_result"),
	MANUAL_EXAMINATION_RESULT("manual_examination_result"),
	NOTIFY_LETTER_TRANSMISSION_RESULT("notifyLetterTransmission_result"),
	OPEN_TASK_RESULT("openTasks_result"),
	POSSIBLE_EXAMINE_EXAMINATION("possibleExamine_examination"),
	POSSIBLE_EXAMINE_RESULT("possibleExamine_result"),
	PREREGISTRATON_CONTROL_POINT_RESULT("preregistration_control_point_result"),
	RECALCULATE_FEES_RESULT("recalculateFees_result"),
	RECORDAL_PUBLICATION_INFO("recordalPublicationInfo"),
	RE_EXAMINE_AFTER_APPEAL_RESULT("reExamineAfterAppeal_result"),
	RE_EXAMINE_REQUEST_RESULT("RE_EXAMINE_REQUEST_RESULT"),
	SEND_DOCS_BY_ECOMM_RESULT("sendDocsByEcomm_result"),
	SEND_REPROGRAPHY("sendReprography_result"),
	TRANSLATION_ID_REQUEST("idRequest"),
	TRANSLATION_REQUEST("translationRequest"),

	// Variables
	ANNOTATION_TEXT("annotation_text"),
	APPEALS_PENDING("appealsPending"),
	APPEAL_ID("appealId"),
	CREATE_DOCUMENT_RESULT("tempDocumentIdentifier"),
	DEBIT_LATER_DATE("debitLaterDate"),
	DOCUMENT_GENERATED_AUTO("documentGenerated_auto"),
	DOSSIER("dossier"),
	DOSSIER_CREATOR("dossierCreator"),
	DOSSIER_ID("dossierId"),
	DOSSIER_ID_SOURCE("dossierIdSource"),
	DOSSIER_KIND("dossierKind"),
	DOSSIER_LANGUAGE("dossierLanguage"),
	DOSSIER_OPERATION_TARGET("dossier_operation_target"),
	DOSSIER_SUBKIND("dossierSubkind"),
	DOSSIER_SUBKIND_DESCRIPTION("dossierSubkindDescription"),
	ERROR_COMMENTS("errorComments"),
	ERROR_IP_RIGHTS("errorIpRights"),
	ERROR_LETTER("errorLetter"),
	ERROR_MESSAGE("errorMessage"),
	EXAMINATION_TYPE("examinationType"),
	EXECUTION_RESULT("execution_result"),
	FEE("fee"),
	FEES("fees"),
	FEES_BUSINESS_RULES("fees_business_rules"),
	FEE_ID("feeId"),
	FILTERED_PERSONS("filteredPersons"),
	GS_PART("gsPart"),
	HISTORY_ORDER("historyOrder"),
	IS_FIRST_FEE_REQUIRED("isFirstFeeRequired"),
	LAST_APPEAL("lastAppeal"),
	LEGAL_STATUS("legalStatus"),
	LETTER("letter"),
	LETTERS("letters"),
	LETTER_AUTOMATIC_FLOW("automatic"),
	LETTER_CODE("letterCode"),
	MAIL_ID("mailId"),
	MAIL_INDEX("mailIndex"),
	MANUAL("manual"),
	MIGRATION("migration"),
	MILESTONE("milestone"),
	OPEN_APPEALS_IDS("openAppealsIds"),
	PARTIAL_PAYMENT("partialPayment"),
	PAYMENT("payment"),
	PAYMENT_ID("paymentId"),
	PERSON("person"),
	PERSONS("persons"),
	PERSONS_MAP("personsMap"),
	PERSON_IDENTIFIER("personIdentifier"),
	PERSON_KIND("personKind"),
	PROCEEDING_ID("proceedingId"),
	PUBLICATION_BULLETIN_NUMBER("bulletinNumber"),
	PUBLICATION_CLIENT_REF("clientReference"),
	PUBLICATION_DATE("publicationDate"),
	PUBLICATION_ID("publicationId"),
	PUBLICATION_TYPE("publicationType"),
	PUB_DOSSIERS("pubDossiers"), 
	RECEIVE_MODE("receiveMode"),
	EFILING_REQUEST("eFilingRequest"),
	RECIPIENTS("recipients"),
	RECIPIENT_KIND("recipientKind"),
	REGISTRATION_DELAY("registrationDelay"),
	REGRESSION("regression"),
	REGRESSION_CODE("regressionCode"),
	REFUND_DOSSIER_ID("refund_dossierId"),
	REMAINING_AMOUNT_TO_PAY("remainingAmountToPay"),
	REMINDER("reminder"),
	REQUESTOR("requestor"),
	SENDING_MODE("sendingMode"),
	SENDING_MODE_ARRAY("sendingModeArray"),
	STATUS("status"),
	TASK_COMMENTS("taskComments"),
	TASK_ID("taskId"),
	TASK_KEY("taskKey"),
	TASK_NAME("taskName"),
	TASK_RESULT("taskResult"),
	TEMP_DOCUMENT_IDENTIFIER("tempDocumentIdentifier"),
	TIMELIMIT("timeLimit"),
	TIMELIMIT_MIGRATION("timeLimitMigration"),
	TRANSLATION_REQUEST_INITIAL_INFO("translationRequestInitialInfo"),
	TRANSLATION_COMPOSITION_SOURCE("translation_composition_source"),
	TRANSLATION_COMPOSITION_TARGET("translation_composition_target"),
	USER("user"),
	VALID_DOSSIER_ID("validDossierId"),
	VERSION("version"),
	SKIP_WITHDRAW("skipWithdraw"),
	SPECIAL_CASE("specialCase"),
	TASK_KEY_NOTIFICATION("taskKeyNotification"),
	RENEWAL_DETAILS("renewalDetails"),
	WAIT_FOR_PERFORM_ENTRY_IN_REGISTER("waitForPerformEntryInRegister"),
	EXPIRATION_DATE_TYPE("expirationDateType"),
	SEND_AUTOMATIC("send_automatic"),
    TRANSLATION_REQUIRED("translation_required"),
	SKIP_COPY_DOCUMENT("skipCopyDocument"),
    MANAGE_PERSON_REQUIRED("manage_person_required"),
	TRANSLATION_ALREADY_PERFORMED("translation_already_performed"),
	LEGAL_DATE("legalDate"),
	FEE_TYPE("feeType"),
	TIMELIMIT_TO_CREATE_SURCHARGE_FEE("timeLimitToCreateSurchargeFee"),
	TIMELIMIT_FEE_VALIDATION("timeLimitFeeValidation"),
	IS_DIRECT_PAYMENT("isDirectPayment");

	//@formatter:on

	/**
	 * The value
	 */
	private String value;

	/**
	 * The value constructor.
	 * 
	 * @param value
	 */
	private Variable(final String value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}
}
