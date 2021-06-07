package com.dunin.medicalvaccinatesystem.rest.aop;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Problem {
	private final int status;
	private final String title;
	private final String detail;
}
