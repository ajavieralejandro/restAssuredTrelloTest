package trello.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Prefs {

    private String permissionLevel;
    private boolean hideVotes;
    private String voting;
    private String comments;
    private String invitations;
    private boolean selfJoin;
    private boolean cardCovers;
    private boolean isTemplate;
    private String cardAging;
    private boolean calendarFeedEnabled;
    private ArrayList<Object> hiddenPluginBoardButtons;
    private ArrayList<SwitcherView> switcherViews;
    private String background;
    private String backgroundColor;
    private Object backgroundImage;
    private Object backgroundImageScaled;
    private boolean backgroundTile;
    private String backgroundBrightness;
    private String backgroundBottomColor;
    private String backgroundTopColor;
    private boolean canBePublic;
    private boolean canBeEnterprise;
    private boolean canBeOrg;
    private boolean canBePrivate;
    private boolean canInvite;

    public String getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public boolean isHideVotes() {
        return hideVotes;
    }

    public void setHideVotes(boolean hideVotes) {
        this.hideVotes = hideVotes;
    }

    public String getVoting() {
        return voting;
    }

    public void setVoting(String voting) {
        this.voting = voting;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getInvitations() {
        return invitations;
    }

    public void setInvitations(String invitations) {
        this.invitations = invitations;
    }

    public boolean isSelfJoin() {
        return selfJoin;
    }

    public void setSelfJoin(boolean selfJoin) {
        this.selfJoin = selfJoin;
    }

    public boolean isCardCovers() {
        return cardCovers;
    }

    public void setCardCovers(boolean cardCovers) {
        this.cardCovers = cardCovers;
    }
    @JsonProperty("isTemplate")
    public boolean isTemplate() {
        return isTemplate;
    }
    @JsonProperty("isTemplate")
    public void setTemplate(boolean template) {
        isTemplate = template;
    }

    public String getCardAging() {
        return cardAging;
    }

    public void setCardAging(String cardAging) {
        this.cardAging = cardAging;
    }

    public boolean isCalendarFeedEnabled() {
        return calendarFeedEnabled;
    }

    public void setCalendarFeedEnabled(boolean calendarFeedEnabled) {
        this.calendarFeedEnabled = calendarFeedEnabled;
    }

    public ArrayList<Object> getHiddenPluginBoardButtons() {
        return hiddenPluginBoardButtons;
    }

    public void setHiddenPluginBoardButtons(ArrayList<Object> hiddenPluginBoardButtons) {
        this.hiddenPluginBoardButtons = hiddenPluginBoardButtons;
    }

    public ArrayList<SwitcherView> getSwitcherViews() {
        return switcherViews;
    }

    public void setSwitcherViews(ArrayList<SwitcherView> switcherViews) {
        this.switcherViews = switcherViews;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Object getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Object backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Object getBackgroundImageScaled() {
        return backgroundImageScaled;
    }

    public void setBackgroundImageScaled(Object backgroundImageScaled) {
        this.backgroundImageScaled = backgroundImageScaled;
    }

    public boolean isBackgroundTile() {
        return backgroundTile;
    }

    public void setBackgroundTile(boolean backgroundTile) {
        this.backgroundTile = backgroundTile;
    }

    public String getBackgroundBrightness() {
        return backgroundBrightness;
    }

    public void setBackgroundBrightness(String backgroundBrightness) {
        this.backgroundBrightness = backgroundBrightness;
    }

    public String getBackgroundBottomColor() {
        return backgroundBottomColor;
    }

    public void setBackgroundBottomColor(String backgroundBottomColor) {
        this.backgroundBottomColor = backgroundBottomColor;
    }

    public String getBackgroundTopColor() {
        return backgroundTopColor;
    }

    public void setBackgroundTopColor(String backgroundTopColor) {
        this.backgroundTopColor = backgroundTopColor;
    }

    public boolean isCanBePublic() {
        return canBePublic;
    }

    public void setCanBePublic(boolean canBePublic) {
        this.canBePublic = canBePublic;
    }

    public boolean isCanBeEnterprise() {
        return canBeEnterprise;
    }

    public void setCanBeEnterprise(boolean canBeEnterprise) {
        this.canBeEnterprise = canBeEnterprise;
    }

    public boolean isCanBeOrg() {
        return canBeOrg;
    }

    public void setCanBeOrg(boolean canBeOrg) {
        this.canBeOrg = canBeOrg;
    }

    public boolean isCanBePrivate() {
        return canBePrivate;
    }

    public void setCanBePrivate(boolean canBePrivate) {
        this.canBePrivate = canBePrivate;
    }

    public boolean isCanInvite() {
        return canInvite;
    }

    public void setCanInvite(boolean canInvite) {
        this.canInvite = canInvite;
    }


}
