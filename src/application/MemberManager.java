package application;
/**
 * <h1>COMP2130 Assignment 2</h1>
 * 
 * @author Emmanuel Sogelola - 101203022
 * @author Stefan Maric
 * @author Kevin Sabas
 *
 */
public class MemberManager {
	private int idSeed;
	private Member[] members;
	private int numMembers, maxMembers;

	public MemberManager(int maxMembers) {
		this.idSeed = 100;
		numMembers = 0;
		this.maxMembers = maxMembers;
		members = new Member[maxMembers];

	}

	public boolean addMember(String firstName, String lastName, int numGames, int numWins) {

		if (numMembers < maxMembers) {
			members[numMembers] = new Member(idSeed, firstName, lastName, numGames, numWins);
			idSeed++;
			numMembers++;
			return true;
		}

		return false;
	}

	public Member[] allMembers() {

		return members;
	}

	public String bestMembers() {
		String s = "The Member(s) with the highest win percentage(s) are: \n";
		int maxI = 0;
		for (int i = 0; i < numMembers; i++) {
			if (members[i].getWinRatio() > members[maxI].getWinRatio()) {
				maxI = i;
			}
		}

		for (int i = 0; i < numMembers; i++) {
			if (members[i].getWinRatio() == members[maxI].getWinRatio()) {
				s += members[i].getId() + " - " + members[i].getFirstName() + " " + members[i].getLastName() + " with "
						+ members[i].getWinPercentage() + "!" + "\n";
			}
		}
		return s;
	}

	public boolean deleteMember(int id) {
		int loc = findMember(id);
		if (loc != -1) {
			members[loc] = members[numMembers - 1];
			numMembers--;
			return true;
		}
		return false;

	}

	public int findMember(int id) {
		for (int i = 0; i < numMembers; i++) {
			if (members[i].getId() == id) {
				return i;
			}
		}
		return -1;

	}

	public int getNumMembers() {
		return numMembers;
	}

	public String mostWins() {
		String s = "The Member(s) with the most wins are: \n";
		int maxI = 0;
		for (int i = 0; i < numMembers; i++) {
			if (members[i].getNumWins() > members[maxI].getNumWins()) {
				maxI = i;
			}
		}

		for (int i = 0; i < numMembers; i++) {
			if (members[i].getNumWins() == members[maxI].getNumWins()) {
				s += members[i].getId() + " - " + members[i].getFirstName() + " " + members[i].getLastName() + " with "
						+ members[i].getNumWins() + " wins!" + "\n";
			}
		}
		return s;
	}

	public boolean setFirstName(int id, String firstName) {
		int loc = findMember(id);
		if (loc != -1) {
			members[loc].setFirstName(firstName);
			return true;
		}
		return false;

	}

	public boolean setGamesPlayed(int id, int num) {
		int loc = findMember(id);
		if (loc != -1) {
			members[loc].setNumGamesPlayed(num);
			return true;
		}
		return false;

	}

	public boolean setLastName(int id, String lastName) {
		int loc = findMember(id);
		if (loc != -1) {
			members[loc].setLastName(lastName);
			return true;
		}
		return false;
	}

	public boolean setNumWins(int id, int num) {
		int loc = findMember(id);
		if (loc != -1) {
			members[loc].setNumWins(num);
			return true;
		}
		return false;

	}

	@Override
	public String toString() {
		String s = "\n------------------------------------------\n";
		for (int i = 0; i < numMembers; i++) {
			s += members[i].toString();
		}
		return s;
	}

	public boolean updateLosses(int id) {
		int loc = findMember(id);
		if (loc != -1) {
			members[loc].updateLosses();
			return true;
		}
		return false;
	}
}
