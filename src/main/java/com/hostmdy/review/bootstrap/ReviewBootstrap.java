package com.hostmdy.review.bootstrap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hostmdy.review.domain.Admin;
import com.hostmdy.review.domain.Battery;
import com.hostmdy.review.domain.Body;
import com.hostmdy.review.domain.Camera;
import com.hostmdy.review.domain.Display;
import com.hostmdy.review.domain.DisplayType;
import com.hostmdy.review.domain.Phone;
import com.hostmdy.review.domain.Platform;
import com.hostmdy.review.domain.Review;
import com.hostmdy.review.domain.Sensors;
import com.hostmdy.review.repository.PhoneRepository;
import com.hostmdy.review.service.AdminService;

@Component
public class ReviewBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	public AdminService adminService;

	@Autowired
	public PhoneRepository phoneRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		phoneRepository.saveAll(createPhoneReviews());
	}

	private List<Phone> createPhoneReviews() {
		// Admin
		final Admin admin = new Admin();
		admin.setName("admin");
		admin.setPassword("admin1234");
		adminService.createAdmin(admin);

		// Phone
		Phone redmiK60 = new Phone();
		redmiK60.setName("Xiaomi Redmi K60");
		redmiK60.setBrand("Xiaomi");
		redmiK60.setModel("K60");
		redmiK60.setLaunch(LocalDate.of(2022, 12, 27));
		redmiK60.setPrice(399.99);
		redmiK60.setMemory("128/8GB, 256/8GB, 256/12GB, 512/12GB, 512/16GB, 1TB/16GB");

		List<Sensors> k60sensors = new ArrayList<>();
		k60sensors.add(Sensors.ACCELEROMETER);
		k60sensors.add(Sensors.COMPASS);
		k60sensors.add(Sensors.GYRO);
		k60sensors.add(Sensors.PROXIMITY);
		k60sensors.add(Sensors.UNDER_DISPLAY_FINGERPRINT);

		redmiK60.setSensors(k60sensors);

		// Phone-Platform
		Platform k60platform = new Platform();
		k60platform.setChipset("Snapdragon 8+ Gen 1");
		k60platform.setCpu("Octa-core (1x3.0 GHz Cortex-X2 & 3x2.5 GHz Cortex-A710 & 4x1.80 GHz Cortex-A510");
		k60platform.setGpu("Adreno 730");
		k60platform.setOs("Android 13, MIUI 14");
		redmiK60.setPlatform(k60platform);

		// Phone-Body
		Body k60Body = new Body();
		k60Body.setDimensions("162.8 x 75.4 x 8.6 mm");
		k60Body.setHeadphone_jack(false);
		k60Body.setUsb_type("USB Type-C 2.0, OTG");
		k60Body.setWeight(200.0);
		redmiK60.setBody(k60Body);

		// Phone-Battery
		Battery k60Battery = new Battery();
		k60Battery.setCapacity(5500);
		k60Battery.setCharging("67W wired, 30W wireless");
		k60Battery.setType("Li-Po");
		redmiK60.setBattery(k60Battery);

		// Phone-Displays
		Display k60Display = new Display();
		k60Display.setName("Main Display");
		k60Display.setType(DisplayType.OLED);
		k60Display.setAspect_ratio("20:9 ratio (~526 ppi density)");
		k60Display.setRefresh_rate("120 Hz");
		k60Display.setResolution("1440 x 3200 px, WQHD+");
		k60Display.setSize("6.67 inches, 107.4 cm2 (~87.5% screen-to-body ratio)");
		k60Display.setPhone(redmiK60);

		redmiK60.getDisplays().add(k60Display);

		// Phone-Cameras
		Camera mainCamera = new Camera("Main Camera", "64 Mpx", "ƒ/1.79",
				"8K@24fps, 4K@30/60fps, 1080p@30/60/120/240/960fps, 720p@1920fps", redmiK60);
		Camera wideAngleCamera = new Camera("Wide Angle Camera", "8 Mpx", "ƒ/2.2", "1080p@30/60/120, 720p@1920fps",
				redmiK60);
		Camera macroCamera = new Camera("Macro Camera", "2 Mpx", "ƒ/2.4", "Unavailable", redmiK60);
		Camera selfieCamera = new Camera("Selfie Camera", "16 Mpx", "ƒ/2.5", "1080p@30/60/120fps", redmiK60);

		redmiK60.getCameras().add(mainCamera);
		redmiK60.getCameras().add(wideAngleCamera);
		redmiK60.getCameras().add(macroCamera);
		redmiK60.getCameras().add(selfieCamera);

		// Phone-Review
		Review designReview = new Review("Design", "K60 is not much outstanding in design and looks rather ordinary. "
				+ "That’s why we purposely bought this blue vegan leather version. "
				+ "The vegan leather back panel has a stitched texture that looks a bit like the Vivo X Note. "
				+ "The vegan leather material really makes the budget phone look like a flagship, also lighter.",
				redmiK60);

		Review displayReview = new Review("Display", "If I’m right, you bought the K60 for the screen, right? "
				+ "Indeed, this screen, like the K50, is unbeatable in its price range. "
				+ "And you can only get to know how good the super high resolution and straight screen are by getting it in your hand. "
				+ "Look at these specs, I couldn’t believe it till right now.\r\n"
				+ "The difference with the K50 is that the manufacturer of the screen this time is China Star Optoelectronics Technology, not Samsung. "
				+ "The advantage is that the peak brightness is higher, and there is no side view color shift problem like Samsung E5 or E6 panels. "
				+ "The higher frequency PWM dimming also means less eye irritation. "
				+ "The downside is that the peak brightness is not as good as the latest Samsung E6 panel, and the screen protection glass on this K60 is no longer Corning Victus. "
				+ "So all in all, the K50 and K60 screens are about the same level.", redmiK60);

		Review cameraReview = new Review("Camera",
				"Although Redmi says that the K60 series has an upgraded camera system this time, they are talking about the Pro version and the standard version still performs average. "
						+ "Let me quickly summarize its camera performance with you. The colors are rich and have high contrast in most scenes. "
						+ "Clarity is also good, and it doesn’t blur even when zoomed in 2x when there’s enough light. "
						+ "The dynamic range of both lenses will be substantially improved after the night mode is on. "
						+ "But with the ultra-wide angle camera at night the clarity is significantly reduced, like other budget phones.\r\n"
						+ "\r\n"
						+ "All in all, the main camera can handle most scenes, the ultra-wide angle camera is only recommended for use during the day.",
				redmiK60);

		Review batteryReview = new Review("Battery",
				"The battery capacity and charging power is the same as last year’s K50, both 67w and 5500mAh. "
						+ "Although the charging power is the same as the Xiaomi 13, the larger battery makes it take longer to fully charge. "
						+ "But the big battery has its advantages. "
						+ "When compared to the 12S Ultra, which also uses 8+ Gen 1, the benefits of a larger battery are obvious. "
						+ "If you adjust the resolution to FHD+ and down the refresh rate, you get a high-performance phone that can be used for two days without charging.",
				redmiK60);

		redmiK60.getReviews().add(designReview);
		redmiK60.getReviews().add(displayReview);
		redmiK60.getReviews().add(cameraReview);
		redmiK60.getReviews().add(batteryReview);
		
		
		//Phone
		Phone s23Ultra = new Phone();
		s23Ultra.setName("Samsung Galaxy S23 Ultra");
		s23Ultra.setBrand("Samsung");
		s23Ultra.setModel("S23 Ultra");
		s23Ultra.setLaunch(LocalDate.of(2023, 02, 17));
		s23Ultra.setPrice(899.99);
		s23Ultra.setMemory("128/8GB, 256/8GB, 256/12GB, 512/12GB, 512/16GB, 1TB/16GB");

		List<Sensors> s23Sensors = new ArrayList<>();
		s23Sensors.add(Sensors.ACCELEROMETER);
		s23Sensors.add(Sensors.COMPASS);
		s23Sensors.add(Sensors.GYRO);
		s23Sensors.add(Sensors.PROXIMITY);
		s23Sensors.add(Sensors.UNDER_DISPLAY_FINGERPRINT);

		s23Ultra.setSensors(s23Sensors);

		// Phone-Platform
		Platform s23Platform = new Platform();
		s23Platform.setChipset("Qualcomm SM8550-AC Snapdragon 8 Gen 2 (4 nm)");
		s23Platform.setCpu("Octa-core (1x3.36 GHz Cortex-X3 & 2x2.8 GHz Cortex-A715 & 2x2.8 GHz Cortex-A710 & 3x2.0 GHz Cortex-A510)");
		s23Platform.setGpu("Adreno 740");
		s23Platform.setOs("Android 13, upgradable to Android 14, One UI 6");
		s23Ultra.setPlatform(s23Platform);

		// Phone-Body
		Body s23Body = new Body();
		s23Body.setDimensions("163.4 x 78.1 x 8.9 mm (6.43 x 3.07 x 0.35 in)");
		s23Body.setHeadphone_jack(false);
		s23Body.setUsb_type("USB Type-C 3.2, OTG");
		s23Body.setWeight(234.0);
		s23Ultra.setBody(s23Body);

		// Phone-Battery
		Battery s23Battery = new Battery();
		s23Battery.setCapacity(5000);
		s23Battery.setCharging("45W wired, 15W wireless");
		s23Battery.setType("Li-Ion");
		s23Ultra.setBattery(s23Battery);

		// Phone-Displays
		Display s23Display = new Display();
		s23Display.setName("Main Display");
		s23Display.setType(DisplayType.AMOLED);
		s23Display.setAspect_ratio("20:9 ratio (~526 ppi density)");
		s23Display.setRefresh_rate("120 Hz");
		s23Display.setResolution("1440 x 3200 px, WQHD+");
		s23Display.setSize("6.67 inches, 107.4 cm2 (~87.5% screen-to-body ratio)");
		s23Display.setPhone(s23Ultra);

		s23Ultra.getDisplays().add(s23Display);

		// Phone-Cameras
		Camera s23mainCamera = new Camera("Main Camera", "64 Mpx", "ƒ/1.79",
				"8K@24fps, 4K@30/60fps, 1080p@30/60/120/240/960fps, 720p@1920fps", s23Ultra);
		Camera s23wideAngleCamera = new Camera("Wide Angle Camera", "8 Mpx", "ƒ/2.2", "1080p@30/60/120, 720p@1920fps",
												s23Ultra);
		Camera s23macroCamera = new Camera("Macro Camera", "2 Mpx", "ƒ/2.4", "Unavailable", s23Ultra);
		Camera s23selfieCamera = new Camera("Selfie Camera", "16 Mpx", "ƒ/2.5", "1080p@30/60/120fps", s23Ultra);

		s23Ultra.getCameras().add(s23mainCamera);
		s23Ultra.getCameras().add(s23wideAngleCamera);
		s23Ultra.getCameras().add(s23macroCamera);
		s23Ultra.getCameras().add(s23selfieCamera);

		// Phone-Review
		Review s23designReview = new Review("Design", "K60 is not much outstanding in design and looks rather ordinary. "
				+ "That’s why we purposely bought this blue vegan leather version. "
				+ "The vegan leather back panel has a stitched texture that looks a bit like the Vivo X Note. "
				+ "The vegan leather material really makes the budget phone look like a flagship, also lighter.",
				s23Ultra);

		Review s23displayReview = new Review("Display", "If I’m right, you bought the K60 for the screen, right? "
				+ "Indeed, this screen, like the K50, is unbeatable in its price range. "
				+ "And you can only get to know how good the super high resolution and straight screen are by getting it in your hand. "
				+ "Look at these specs, I couldn’t believe it till right now.\r\n"
				+ "The difference with the K50 is that the manufacturer of the screen this time is China Star Optoelectronics Technology, not Samsung. "
				+ "The advantage is that the peak brightness is higher, and there is no side view color shift problem like Samsung E5 or E6 panels. "
				+ "The higher frequency PWM dimming also means less eye irritation. "
				+ "The downside is that the peak brightness is not as good as the latest Samsung E6 panel, and the screen protection glass on this K60 is no longer Corning Victus. "
				+ "So all in all, the K50 and K60 screens are about the same level.", s23Ultra);

		Review s23cameraReview = new Review("Camera",
				"Although Redmi says that the K60 series has an upgraded camera system this time, they are talking about the Pro version and the standard version still performs average. "
						+ "Let me quickly summarize its camera performance with you. The colors are rich and have high contrast in most scenes. "
						+ "Clarity is also good, and it doesn’t blur even when zoomed in 2x when there’s enough light. "
						+ "The dynamic range of both lenses will be substantially improved after the night mode is on. "
						+ "But with the ultra-wide angle camera at night the clarity is significantly reduced, like other budget phones.\r\n"
						+ "\r\n"
						+ "All in all, the main camera can handle most scenes, the ultra-wide angle camera is only recommended for use during the day.",
						s23Ultra);

		Review s23batteryReview = new Review("Battery",
				"The battery capacity and charging power is the same as last year’s K50, both 67w and 5500mAh. "
						+ "Although the charging power is the same as the Xiaomi 13, the larger battery makes it take longer to fully charge. "
						+ "But the big battery has its advantages. "
						+ "When compared to the 12S Ultra, which also uses 8+ Gen 1, the benefits of a larger battery are obvious. "
						+ "If you adjust the resolution to FHD+ and down the refresh rate, you get a high-performance phone that can be used for two days without charging.",
						s23Ultra);

		s23Ultra.getReviews().add(s23designReview);
		s23Ultra.getReviews().add(s23displayReview);
		s23Ultra.getReviews().add(s23cameraReview);
		s23Ultra.getReviews().add(s23batteryReview);
		
		
		//Phone
				Phone googlePixel8 = new Phone();
				googlePixel8.setName("Google Pixel 8");
				googlePixel8.setBrand("Google");
				googlePixel8.setModel("Pixel 8");
				googlePixel8.setLaunch(LocalDate.of(2023, 02, 17));
				googlePixel8.setPrice(899.99);
				googlePixel8.setMemory("128/8GB, 256/8GB, 256/12GB, 512/12GB, 512/16GB, 1TB/16GB");

				List<Sensors> pixel8Sensors = new ArrayList<>();
				pixel8Sensors.add(Sensors.ACCELEROMETER);
				pixel8Sensors.add(Sensors.COMPASS);
				pixel8Sensors.add(Sensors.GYRO);
				pixel8Sensors.add(Sensors.PROXIMITY);
				pixel8Sensors.add(Sensors.UNDER_DISPLAY_FINGERPRINT);

				googlePixel8.setSensors(pixel8Sensors);

				// Phone-Platform
				Platform pixel8Platform = new Platform();
				pixel8Platform.setChipset("Qualcomm SM8550-AC Snapdragon 8 Gen 2 (4 nm)");
				pixel8Platform.setCpu("Octa-core (1x3.36 GHz Cortex-X3 & 2x2.8 GHz Cortex-A715 & 2x2.8 GHz Cortex-A710 & 3x2.0 GHz Cortex-A510)");
				pixel8Platform.setGpu("Adreno 740");
				pixel8Platform.setOs("Android 13, upgradable to Android 14, One UI 6");
				googlePixel8.setPlatform(pixel8Platform);

				// Phone-Body
				Body pixel8Body = new Body();
				pixel8Body.setDimensions("163.4 x 78.1 x 8.9 mm (6.43 x 3.07 x 0.35 in)");
				pixel8Body.setHeadphone_jack(false);
				pixel8Body.setUsb_type("USB Type-C 3.2, OTG");
				pixel8Body.setWeight(234.0);
				googlePixel8.setBody(pixel8Body);

				// Phone-Battery
				Battery pixel8Battery = new Battery();
				pixel8Battery.setCapacity(5000);
				pixel8Battery.setCharging("45W wired, 15W wireless");
				pixel8Battery.setType("Li-Ion");
				googlePixel8.setBattery(pixel8Battery);

				// Phone-Displays
				Display pixel8Display = new Display();
				pixel8Display.setName("Main Display");
				pixel8Display.setType(DisplayType.AMOLED);
				pixel8Display.setAspect_ratio("20:9 ratio (~526 ppi density)");
				pixel8Display.setRefresh_rate("120 Hz");
				pixel8Display.setResolution("1440 x 3200 px, WQHD+");
				pixel8Display.setSize("6.67 inches, 107.4 cm2 (~87.5% screen-to-body ratio)");
				pixel8Display.setPhone(googlePixel8);

				googlePixel8.getDisplays().add(pixel8Display);

				// Phone-Cameras
				Camera pixel8mainCamera = new Camera("Main Camera", "64 Mpx", "ƒ/1.79",
						"8K@24fps, 4K@30/60fps, 1080p@30/60/120/240/960fps, 720p@1920fps", googlePixel8);
				Camera pixel8wideAngleCamera = new Camera("Wide Angle Camera", "8 Mpx", "ƒ/2.2", "1080p@30/60/120, 720p@1920fps",
						googlePixel8);
				Camera pixel8macroCamera = new Camera("Macro Camera", "2 Mpx", "ƒ/2.4", "Unavailable", googlePixel8);
				Camera pixel8selfieCamera = new Camera("Selfie Camera", "16 Mpx", "ƒ/2.5", "1080p@30/60/120fps", googlePixel8);

				googlePixel8.getCameras().add(pixel8mainCamera);
				googlePixel8.getCameras().add(pixel8wideAngleCamera);
				googlePixel8.getCameras().add(pixel8macroCamera);
				googlePixel8.getCameras().add(pixel8selfieCamera);

				// Phone-Review
				Review pixel8designReview = new Review("Design", "K60 is not much outstanding in design and looks rather ordinary. "
						+ "That’s why we purposely bought this blue vegan leather version. "
						+ "The vegan leather back panel has a stitched texture that looks a bit like the Vivo X Note. "
						+ "The vegan leather material really makes the budget phone look like a flagship, also lighter.",
						googlePixel8);

				Review pixel8displayReview = new Review("Display", "If I’m right, you bought the K60 for the screen, right? "
						+ "Indeed, this screen, like the K50, is unbeatable in its price range. "
						+ "And you can only get to know how good the super high resolution and straight screen are by getting it in your hand. "
						+ "Look at these specs, I couldn’t believe it till right now.\r\n"
						+ "The difference with the K50 is that the manufacturer of the screen this time is China Star Optoelectronics Technology, not Samsung. "
						+ "The advantage is that the peak brightness is higher, and there is no side view color shift problem like Samsung E5 or E6 panels. "
						+ "The higher frequency PWM dimming also means less eye irritation. "
						+ "The downside is that the peak brightness is not as good as the latest Samsung E6 panel, and the screen protection glass on this K60 is no longer Corning Victus. "
						+ "So all in all, the K50 and K60 screens are about the same level.", googlePixel8);

				Review pixel8cameraReview = new Review("Camera",
						"Although Redmi says that the K60 series has an upgraded camera system this time, they are talking about the Pro version and the standard version still performs average. "
								+ "Let me quickly summarize its camera performance with you. The colors are rich and have high contrast in most scenes. "
								+ "Clarity is also good, and it doesn’t blur even when zoomed in 2x when there’s enough light. "
								+ "The dynamic range of both lenses will be substantially improved after the night mode is on. "
								+ "But with the ultra-wide angle camera at night the clarity is significantly reduced, like other budget phones.\r\n"
								+ "\r\n"
								+ "All in all, the main camera can handle most scenes, the ultra-wide angle camera is only recommended for use during the day.",
								googlePixel8);

				Review pixel8batteryReview = new Review("Battery",
						"The battery capacity and charging power is the same as last year’s K50, both 67w and 5500mAh. "
								+ "Although the charging power is the same as the Xiaomi 13, the larger battery makes it take longer to fully charge. "
								+ "But the big battery has its advantages. "
								+ "When compared to the 12S Ultra, which also uses 8+ Gen 1, the benefits of a larger battery are obvious. "
								+ "If you adjust the resolution to FHD+ and down the refresh rate, you get a high-performance phone that can be used for two days without charging.",
								googlePixel8);

				googlePixel8.getReviews().add(pixel8designReview);
				googlePixel8.getReviews().add(pixel8displayReview);
				googlePixel8.getReviews().add(pixel8cameraReview);
				googlePixel8.getReviews().add(pixel8batteryReview);

		// Persist(Save)
		List<Phone> phones = new ArrayList<>();
		phones.add(redmiK60);
		phones.add(s23Ultra);
		phones.add(googlePixel8);

		return phones;

	}

}
