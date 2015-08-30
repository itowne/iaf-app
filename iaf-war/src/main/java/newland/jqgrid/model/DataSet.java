package newland.jqgrid.model;
import java.util.List;

/**
 *
 * @author 黄瑞斌
 */
public class DataSet {
	
    // Your result List
    private List<Object> gridModel;
    // get how many rows we want to have into the grid - rowNum attribute in the
    // grid
    private Integer rows = 0;
    // Get the requested page. By default grid sets this to 1.
    private Integer page = 0;
    // sorting order - asc or desc
    private String sord;
    // get index row - i.e. user click to sort.
    private String sidx;
    // Search Field
    private String searchField;
    // The Search String
    private String searchString;
    // he Search Operation
    // ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
    private String searchOper;
    // Your Total Pages
    private Integer total = 0;
    // All Records
    private Integer records = 0;
    
    //单次加载或者多次加载
    private boolean loadonce = false;

    private String nd;
    
    /**
     * 获得一个列表集<p>
     * 在<tt>{@link #loadonce}==true</tt>时,表示的是一个完整的列表,无法期待再次向服务器查询返回请求<p>
     * 在<tt>{@link #loadonce}==true</tt>时,表示的是一个单一页面的列表。<b>若返回条数大于实际页面条数,则之后的记录将被忽略</b><p>
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
	public List getGridModel() {
        return gridModel;
    }

    /**
     * @see #getGridModel()
     * @param gridModel
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void setGridModel(List gridModel) {
        this.gridModel = gridModel;
    }

    /**
     * 是否是单次加载<p>
     * 若单次加载，则一次加载完整数据，不再向服务器发送请求。<p>
     * 若多次加载，则要注意实际返回条数同页数之间的关系。<p>
     * 
     * @see #gridModel
     * @return
     */
    public boolean isLoadonce() {
        return loadonce;
    }

    /**
     * @see DataSet#isLoadonce()
     * 
     * @param loadonce
     */
    public void setLoadonce(boolean loadonce) {
        this.loadonce = loadonce;
    }

    /**
     * 返回页面请求的页数<p>
     * 仅当{@link #loadonce == true}时，点下一页时发起。
     * 
     * @return
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @see DataSet#getPage()
     * 
     * @param page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * 设置查询总条数<p>
     * 总条数决定最终页面的显示<p>
     * 若列表长度大于总条数，则超出部分数据将被忽略。
     *  
     * @return
     */
    public Integer getRecords() {
        return records;
    }

    /**
     * @see #getRecords()
     * 
     * @param records
     */
    public void setRecords(Integer records) {
        this.records = records;
    }

    /**
     * 返回当页条数<p>
     * 
     * @return
     */
    public Integer getRows() {
        return rows;
    }

    
    public void setRows(Integer rows) {
        this.rows = rows;
    }

    
    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchOper() {
        return searchOper;
    }

    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public Integer getTotal() {
        this.total = (int) Math.ceil((double) records / (double) rows);
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public int getTo() {
        int to = (rows * page);
        if (to > records) {
            to = records;
        }
        return to;
    }

    public int getFrom() {
        int to = (rows * page);
        int from = to - rows;
        return from;
    }

    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }
}
