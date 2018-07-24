---
name: Pull request
about: Submit this before working on a pull request

---

**Use Case**
- Describe the motivation to add this new feature and which problems it will tackle.

> It would be nice to have a helper method in the adapter to notify that the visibility of the header of a section was changed.
> It's known that this can be done with the native notifyItemInserted and notifyItemRemoved methods but it would be nice to have the logic to calculate the header position wrapped in a method of the adapter.

**Design**
- Which methods will be created/modified/deleted in the existing classes.
- Which classes will be created/deleted.
- Explanation for each of the changes listed above.

> Create methods:
>
> - notifyHeaderInsertedInSection
>
> Get position with getHeaderPositionInAdapter and call callSuperNotifyItemInserted
>
> - notifyHeaderRemovedInSection
>
> Get position with getSectionPosition and call callSuperNotifyItemInserted

**How to use**
How the developers will use the new methods/classes introduced by these changes.
> Simply call:
>
> adapter.notifyHeaderInsertedInSection(section);
