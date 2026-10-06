package one.lindegaard.MobHunting.compatibility;

final class HerobrineApiIdentity {
	private static final String SUPPORTED_MAIN = "net.theprogrammersworld.herobrine.Herobrine";

	private HerobrineApiIdentity() {
	}

	static boolean supports(String mainClass) {
		return SUPPORTED_MAIN.equals(mainClass);
	}
}
