
public class PassingByReference {

	public static void main(String[] args) {
		int num = 0;
		num = roundNum(num, true);
		System.out.println(num);

	}

	public static int roundNum (double num, boolean b) {
		int res = 0;
		if (b) {
			res = (int)(num+1);
		}
		else {
			res = (int)num;
		}
		return res;
	}
}
