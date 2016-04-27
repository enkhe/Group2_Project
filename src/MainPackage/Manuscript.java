package MainPackage;

/**
 * It was not under original UML but felt the need to have this "Manuscript" class to save 
 * manuscript information. Since Authors in the "Manuscripts" class can not contain itself.
 *
 */

public class Manuscript {

	private Integer authorID;
	private String filePath;
	private String manuscriptName;
	private String manuscriptContent;

	public Manuscript() {
		this(0, "", "", "");
	}

	public Manuscript(Integer _authorID, String _filePath, 
			String _manuscriptName, String _manuscriptContent) {
		setAuthorID(_authorID);
		setFilePath(_filePath);
		setManuscriptContent(_manuscriptContent);
		setManuscriptName(_manuscriptName);
	}

	public Integer getAuthorID() {
		return authorID;
	}

	public void setAuthorID(Integer authorID) {
		this.authorID = authorID;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getManuscriptName() {
		return manuscriptName;
	}

	public void setManuscriptName(String manuscriptName) {
		this.manuscriptName = manuscriptName;
	}

	public String getManuscriptContent() {
		return manuscriptContent;
	}

	public void setManuscriptContent(String manuscriptContent) {
		this.manuscriptContent = manuscriptContent;
	}

}
