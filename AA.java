package PubSubTest;

import com.google.api.core.ApiFuture;

import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.*;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Random;


public class PswriterTest {




    @Test
        //Minimalistic
    void publish() throws Exception {


        System.out.println(
                Publisher.newBuilder(
                                ProjectTopicName.of("pure-loop-417711", "john-topic"))
                        .build()
                        .publish(PubsubMessage.newBuilder()
                                .setData(ByteString.copyFromUtf8(
                                        "Kreupasanam Amma " + new Random().nextInt(900)))
                                .build())
                        .get()
        );
    }




    @Test
    void PublishToPubSubWorking() throws Exception {
        Random random = new Random();
        ProjectTopicName topic = ProjectTopicName.of("pure-loop-417711", "john-topic");
       // ProjectTopicName topic = ProjectTopicName.of("charming-hearth-4171116", "john-topic");
        String payload = "IMS Amma " + random.nextInt(900);
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(ByteString.copyFromUtf8(payload)).build();
        ApiFuture<String> messageIdFuture = Publisher.newBuilder(topic).build().publish(pubsubMessage);
        System.out.println(messageIdFuture.get());

    }


    void PublishToPubSubWorkingFileMultiple() throws IOException {
        String projectId = "pure-loop-417711";
        String topicId = "john-topic";
        ProjectTopicName topic = ProjectTopicName.of(projectId, topicId);
        File folder = new File("D:\\Automation\\pubsub-demo-master\\src\\main\\java\\AllFile");
        File[] listOfFiles = folder.listFiles();
        for (File file : Objects.requireNonNull(listOfFiles)) {
            if (file.isFile()) {
                byte[] fileData = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
                PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(ByteString.copyFrom(fileData)).build();
                Publisher publisher = Publisher.newBuilder(topic).build();
                ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
                String messageId;
                try {
                    messageId = messageIdFuture.get();
                    System.out.println(messageId);
                    System.out.println(file.getAbsolutePath());
                } catch (Exception e) {

                    e.printStackTrace(System.err);
                }
                publisher.shutdown();
            }
        }

    }




}