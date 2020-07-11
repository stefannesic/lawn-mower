public enum Instruction {
	F, L, R;
	
	// converts a string to an instruction
	public static Instruction StringtoInstruction(String i) throws Exception {
		switch (i) {
		case "F":
			return F;
		case "L":
			return L;
		case "R":
			return R;
		default:
			throw new Exception("String does not correspond to a direction");
		}
	}

	// converts strings to array of instructions
	public static Instruction[] StringArraytoInstruction(String[] strings) throws Exception {
		Instruction[] i = new Instruction[strings.length];
		int j = 0;
		for (String s: strings) {
			i[j] = Instruction.StringtoInstruction(s);
			j++;
		}
		return i;
	}
}
