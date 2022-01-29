package com.s3lamda.function;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class S3ObjectPublishRequestHandler implements RequestHandler<S3EventNotification, String> {
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public String handleRequest(S3EventNotification input, Context context) {
		LambdaLogger logger = context.getLogger();

		logger.log("handleRequest() has been called");
		input.getRecords().forEach(r -> {
			logger.log("Size :" + r.getS3().getObject().getSizeAsLong());
		});
		logger.log("CONTEXT : " + gson.toJson(context));
		logger.log("event : " + gson.toJson(input));
		return input.toString();
	}

}
