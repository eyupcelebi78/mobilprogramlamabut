package com.example.myproject.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.example.myproject.R;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class UsersFragmentDirections {
  private UsersFragmentDirections() {
  }

  @NonNull
  public static ActionUsersFragmentToSingleUserFragment actionUsersFragmentToSingleUserFragment() {
    return new ActionUsersFragmentToSingleUserFragment();
  }

  public static class ActionUsersFragmentToSingleUserFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionUsersFragmentToSingleUserFragment() {
    }

    @NonNull
    public ActionUsersFragmentToSingleUserFragment setId(int id) {
      this.arguments.put("id", id);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("id")) {
        int id = (int) arguments.get("id");
        __result.putInt("id", id);
      } else {
        __result.putInt("id", -1);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_usersFragment_to_singleUserFragment;
    }

    @SuppressWarnings("unchecked")
    public int getId() {
      return (int) arguments.get("id");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionUsersFragmentToSingleUserFragment that = (ActionUsersFragmentToSingleUserFragment) object;
      if (arguments.containsKey("id") != that.arguments.containsKey("id")) {
        return false;
      }
      if (getId() != that.getId()) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + getId();
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionUsersFragmentToSingleUserFragment(actionId=" + getActionId() + "){"
          + "id=" + getId()
          + "}";
    }
  }
}
